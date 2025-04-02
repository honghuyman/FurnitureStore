package vn.edu.hcmut.furniturestore.adapter.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.hcmut.furniturestore.R
import vn.edu.hcmut.furniturestore.data.Product
import vn.edu.hcmut.furniturestore.data.fabrics
import vn.edu.hcmut.furniturestore.data.products
import vn.edu.hcmut.furniturestore.adapter.arview.ARCoachingOverlay
import vn.edu.hcmut.furniturestore.adapter.arview.ARView
import vn.edu.hcmut.furniturestore.adapter.arview.GestureOverlay
import kotlinx.coroutines.delay

@Composable
fun ARScreen()   {

    var showCoachingOverlay by remember { mutableStateOf(true) }
    var showGestureOverlay by remember { mutableStateOf(false) }
    var showUiControls by remember { mutableStateOf(false) }
    var hasOverlayShown by remember { mutableStateOf(false) }
    val currentModel = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        delay(3000)
        showCoachingOverlay = false
        showUiControls = true
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            ARView(currentModel.value)

            this@Column.AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.Black.copy(alpha = 0.5F)),
                visible = showCoachingOverlay,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ARCoachingOverlay(
                    message = stringResource(R.string.message_scan_horizontal_plane)
                )
            }

            this@Column.AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.Black.copy(alpha = 0.5F)),
                visible = showGestureOverlay,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                GestureOverlay {
                    showGestureOverlay = false
                    hasOverlayShown = true
                }
            }

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterEnd),
            ) {
                this@Column.AnimatedVisibility(
                    visible = showUiControls,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    FabricSelector(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.White)
                            .padding(5.dp),
                        onClick = {}
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.BottomCenter),
            ) {
                this@Column.AnimatedVisibility(
                    visible = showUiControls,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    ProductSelector(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        onClick = {
                            currentModel.value = it
                            if (!hasOverlayShown) {
                                showGestureOverlay = true
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FabricSelector(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(2.dp),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically,
        ),
    ) {
        items(fabrics) {
            Box(
                modifier = Modifier
                    .clickable { onClick(it.name) }
                    .size(30.dp)
            ) {
                Image(
                    modifier = Modifier.clip(CircleShape),
                    painter = painterResource(id = it.imageId),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun ProductSelector(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(products) {
            ProductView(modifier = Modifier.clickable { onClick(it.modelName) }, product = it)
        }
    }
}

@Composable
fun ProductView(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xFFECECEC)),
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    minLines = 1,
                )
                Text(
                    text = product.description,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    minLines = 1,
                )
                Text(
                    text = "$${product.price.toInt()}",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    minLines = 1,
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .align(Alignment.Bottom)
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Add to Cart",
                    tint = Color.Red
                )
            }
        }
    }
}
