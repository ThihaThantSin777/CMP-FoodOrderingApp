package org.thiha.thant.sin.foa.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.thiha.thant.sin.foa.components.AppErrorView
import org.thiha.thant.sin.foa.components.AppLoadingDialog
import org.thiha.thant.sin.foa.components.AppNetworkImage
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.ADD_BUTTON_CONTAINER_SIZE
import org.thiha.thant.sin.foa.core.ADD_BUTTON_SIZE
import org.thiha.thant.sin.foa.core.BOTTOM_TOOL_BAR_COLOR
import org.thiha.thant.sin.foa.core.BOTTOM_TOOL_BAR_HEIGHT
import org.thiha.thant.sin.foa.core.BOTTOM_TOOL_BAR_WIDTH
import org.thiha.thant.sin.foa.core.DEFAULT_BUTTON_HEIGHT
import org.thiha.thant.sin.foa.core.DEFAULT_RESTAURANT_IMAGE_HEIGHT
import org.thiha.thant.sin.foa.core.MARGIN_40
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.MARGIN_SMALL
import org.thiha.thant.sin.foa.core.MENU_IMAGE_HEIGHT
import org.thiha.thant.sin.foa.core.MENU_IMAGE_WIDTH
import org.thiha.thant.sin.foa.core.PRIMARY_COLOR
import org.thiha.thant.sin.foa.core.RESTAURANT_ITEM_PRICE_HEIGHT
import org.thiha.thant.sin.foa.core.RESTAURANT_ITEM_PRICE_WIDTH
import org.thiha.thant.sin.foa.core.RESTAURANT_NOT_FOUNT_TEXT
import org.thiha.thant.sin.foa.core.SECONDARY_COLOR
import org.thiha.thant.sin.foa.core.TEXT_REGULAR
import org.thiha.thant.sin.foa.core.TEXT_SMALL
import org.thiha.thant.sin.foa.core.VIEW_MY_CART_TEXT
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.FoodItemVO
import org.thiha.thant.sin.foa.home.state.RestaurantDetailsState
import org.thiha.thant.sin.foa.home.viewmodel.RestaurantDetailsViewModel

@Composable
fun RestaurantDetailsRoute(
    viewModel: RestaurantDetailsViewModel,
    onTapViewMyCart: () -> Unit,
    onTapBack: () -> Unit
) {
    val restaurantDetailsState by viewModel.state.collectAsStateWithLifecycle()

    RestaurantDetailsScreen(
        restaurantDetailsState = restaurantDetailsState,
        onTapViewMyCart = onTapViewMyCart,
        onTapBack = onTapBack,
        onTapRetry = {
            viewModel.loadRestaurantByID()
        },
        onTapAddItem = { item, isUpdate ->
            viewModel.onSaveFoodItem(item, isUpdate)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailsScreen(
    restaurantDetailsState: RestaurantDetailsState,
    onTapViewMyCart: () -> Unit,
    onTapBack: () -> Unit,
    onTapRetry: () -> Unit,
    onTapAddItem: (FoodItemVO, isUpdate: Boolean) -> Unit,
) {
    val restaurant = restaurantDetailsState.restaurantDetails

    val toolBars = remember(restaurant) {
        restaurant?.foodCategories
            ?.map { it.name }
            ?.takeIf { it.isNotEmpty() }
            ?: listOf("Menu")
    }

    var selectedTab by remember(restaurant) {
        mutableStateOf(toolBars.first())
    }

    var cart by remember { mutableStateOf<Map<Long, FoodItemVO>>(emptyMap()) }
    val cartCount = cart.values.sumOf { it.quantity }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(restaurant?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = { onTapBack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (cartCount > 0) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(
                            horizontal = MARGIN_MEDIUM_2,
                            vertical = MARGIN_CARD_MEDIUM_2
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AppPrimaryButton(
                        onClick = { onTapViewMyCart() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(DEFAULT_BUTTON_HEIGHT),
                        text = VIEW_MY_CART_TEXT,
                    )
                }
            }
        }
    ) { inner ->

        when (restaurantDetailsState.uiState) {
            UiState.LOADING -> {
                AppLoadingDialog()
            }

            UiState.FAIL -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    AppErrorView(
                        message = restaurantDetailsState.errorMessage,
                        onTapRetry = onTapRetry
                    )
                }
            }

            else -> {
                if (restaurant == null) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AppErrorView(
                            message = RESTAURANT_NOT_FOUNT_TEXT,
                            onTapRetry = onTapRetry
                        )
                    }
                    return@Scaffold
                }

                val categories = restaurant.foodCategories
                val selectedCategory = categories.firstOrNull { it.name == selectedTab }
                    ?: categories.firstOrNull()

                val foodItems = selectedCategory?.foodItems.orEmpty()

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(inner)
                ) {


                    item {
                        AppNetworkImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(DEFAULT_RESTAURANT_IMAGE_HEIGHT),
                            imageUrl = restaurant.imageUrl,
                            shape = RoundedCornerShape(0.dp)
                        )
                    }

                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = MARGIN_MEDIUM_2,
                                    bottom = MARGIN_MEDIUM
                                ),
                        ) {
                            items(toolBars) { label ->
                                BottomToolBar(
                                    label = label,
                                    isSelect = label == selectedTab,
                                    onTap = { selectedTab = it }
                                )
                            }
                        }
                        HorizontalDivider()
                    }

                    item { Spacer(Modifier.height(MARGIN_MEDIUM_2)) }

                    item {
                        SectionHeader(
                            text = selectedCategory?.name ?: "",
                        )
                    }

                    items(foodItems) { food ->
                        val vm = MenuItemVM(
                            id = food.id,
                            tag = selectedCategory?.name,
                            title = food.name,
                            subtitle = food.description,
                            price = food.price,
                            image = food.imageUrl
                        )

                        val existingCartItem = cart[vm.id]
                        val count = existingCartItem?.quantity ?: 0

                        MenuRow(
                            data = vm,
                            count = count,
                            onAdd = {
                                val existing = cart[vm.id]
                                val newQty = (existing?.quantity ?: 0) + 1

                                val updatedFood = food.copy(quantity = newQty)

                                cart = cart.toMutableMap().apply {
                                    put(vm.id, updatedFood)
                                }

                                val isUpdate = existing != null
                                onTapAddItem(updatedFood, isUpdate)
                            }
                        )
                    }

                    item { Spacer(Modifier.height(MARGIN_40)) }
                }
            }
        }
    }
}

@Composable
fun BottomToolBar(label: String, isSelect: Boolean, onTap: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = MARGIN_MEDIUM_3)
            .clickable { onTap(label) }
    ) {
        Text(
            label,
            fontWeight = FontWeight.W700,
            fontSize = TEXT_REGULAR,
            color = if (isSelect) Color.Black else SECONDARY_COLOR
        )
        Spacer(Modifier.height(MARGIN_SMALL))
        Box(
            modifier = Modifier
                .width(BOTTOM_TOOL_BAR_WIDTH)
                .height(BOTTOM_TOOL_BAR_HEIGHT)
                .background(
                    if (isSelect) BOTTOM_TOOL_BAR_COLOR else Color.Transparent,
                    RoundedCornerShape(2.dp)
                )
        )
    }
}

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(
            horizontal = MARGIN_MEDIUM_3,
            vertical = MARGIN_MEDIUM
        ),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.SemiBold
        )
    )
}

@Composable
fun MenuRow(
    data: MenuItemVM,
    count: Int,
    onAdd: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MARGIN_MEDIUM_3, vertical = MARGIN_MEDIUM),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (!data.tag.isNullOrBlank()) {
                Text(
                    data.tag,
                    color = SECONDARY_COLOR,
                    fontSize = TEXT_REGULAR,
                    fontWeight = FontWeight.W400
                )
                Spacer(Modifier.height(MARGIN_SMALL))
            }
            Text(
                data.title,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(MARGIN_SMALL))
            Text(
                data.subtitle,
                color = SECONDARY_COLOR
            )
            Spacer(Modifier.height(MARGIN_MEDIUM))

            PriceChip(price = data.price)
        }

        Spacer(Modifier.width(MARGIN_MEDIUM_3))

        Box(
            modifier = Modifier
                .width(MENU_IMAGE_WIDTH)
                .height(MENU_IMAGE_HEIGHT)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2)
                ),
            contentAlignment = Alignment.BottomEnd
        ) {
            AppNetworkImage(
                imageUrl = data.image,
                shape = RoundedCornerShape(MARGIN_MEDIUM)
            )

            AddButton(count = count, onClick = onAdd)
        }
    }
}

@Composable
fun PriceChip(price: Double) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(RESTAURANT_ITEM_PRICE_WIDTH)
            .height(RESTAURANT_ITEM_PRICE_HEIGHT)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                shape = RoundedCornerShape(MARGIN_MEDIUM_3)
            )
            .padding(
                horizontal = MARGIN_CARD_MEDIUM_2,
            )
    ) {
        Text(
            text = "$$price",
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun AddButton(count: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(MARGIN_MEDIUM)
            .size(ADD_BUTTON_CONTAINER_SIZE)
            .background(MaterialTheme.colorScheme.surface, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text("+", fontWeight = FontWeight.Bold)
        if (count > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(ADD_BUTTON_SIZE)
                    .background(MaterialTheme.colorScheme.error, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "$count",
                    color = Color.White,
                    fontSize = TEXT_SMALL,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

data class MenuItemVM(
    val id: Long,
    val tag: String? = null,
    val title: String,
    val subtitle: String,
    val price: Double,
    val image: String
)
