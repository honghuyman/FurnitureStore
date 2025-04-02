//package vn.edu.hcmut.furniturestore.adapter.screens
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.systemBars
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.windowInsetsPadding
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import vn.edu.hcmut.furniturestore.R
//import vn.edu.hcmut.furniturestore.data.Product
//import vn.edu.hcmut.furniturestore.data.fabrics
//import vn.edu.hcmut.furniturestore.data.products
//import vn.edu.hcmut.furniturestore.adapter.arview.ARCoachingOverlay
//import vn.edu.hcmut.furniturestore.adapter.arview.ARView
//import vn.edu.hcmut.furniturestore.adapter.arview.GestureOverlay
//import kotlinx.coroutines.delay
//
//@Composable
//fun ARScreen()   {
//
//    var showCoachingOverlay by remember { mutableStateOf(true) }
//    var showGestureOverlay by remember { mutableStateOf(false) }
//    var showUiControls by remember { mutableStateOf(false) }
//    var hasOverlayShown by remember { mutableStateOf(false) }
//    val currentModel = remember { mutableStateOf("") }
//
//    LaunchedEffect(Unit) {
//        delay(3000)
//        showCoachingOverlay = false
//        showUiControls = true
//    }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f), contentAlignment = Alignment.Center
//        ) {
//            ARView(currentModel.value)
//
//            this@Column.AnimatedVisibility(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .background(Color.Black.copy(alpha = 0.5F)),
//                visible = showCoachingOverlay,
//                enter = fadeIn(),
//                exit = fadeOut()
//            ) {
//                ARCoachingOverlay(
//                    message = stringResource(R.string.message_scan_horizontal_plane)
//                )
//            }
//
//            this@Column.AnimatedVisibility(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .background(Color.Black.copy(alpha = 0.5F)),
//                visible = showGestureOverlay,
//                enter = fadeIn(),
//                exit = fadeOut()
//            ) {
//                GestureOverlay {
//                    showGestureOverlay = false
//                    hasOverlayShown = true
//                }
//            }
//
////            Box(
////                modifier = Modifier
////                    .padding(10.dp)
////                    .align(Alignment.CenterEnd),
////            ) {
////                this@Column.AnimatedVisibility(
////                    visible = showUiControls,
////                    enter = fadeIn(),
////                    exit = fadeOut()
////                ) {
////                    FabricSelector(
////                        modifier = Modifier
////                            .clip(RoundedCornerShape(5.dp))
////                            .background(Color.White)
////                            .padding(5.dp),
////                        onClick = {}
////                    )
////                }
////            }
//
//            Box(
//                modifier = Modifier
//                    .padding(10.dp)
//                    .align(Alignment.BottomCenter),
//            ) {
//                this@Column.AnimatedVisibility(
//                    visible = showUiControls,
//                    enter = fadeIn(),
//                    exit = fadeOut()
//                ) {
//                    ProductSelector(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentHeight(),
//                        onClick = {
//                            currentModel.value = it
//                            if (!hasOverlayShown) {
//                                showGestureOverlay = true
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
//
////@Composable
////fun FabricSelector(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
////    LazyColumn(
////        modifier = modifier,
////        contentPadding = PaddingValues(2.dp),
////        verticalArrangement = Arrangement.spacedBy(
////            16.dp,
////            Alignment.CenterVertically,
////        ),
////    ) {
////        items(fabrics) {
////            Box(
////                modifier = Modifier
////                    .clickable { onClick(it.name) }
////                    .size(30.dp)
////            ) {
////                Image(
////                    modifier = Modifier.clip(CircleShape),
////                    painter = painterResource(id = it.imageId),
////                    contentDescription = null,
////                )
////            }
////        }
////    }
////}
//
//@Composable
//fun ProductSelector(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
//    LazyRow(
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .windowInsetsPadding(WindowInsets.systemBars),
//        horizontalArrangement = Arrangement.spacedBy(12.dp),
//    ) {
//        items(products) {
//            ProductView(modifier = Modifier.clickable { onClick(it.modelName) }, product = it)
//        }
//    }
//}
//
//@Composable
//fun ProductView(
//    product: Product,
//    modifier: Modifier = Modifier,
//) {
//    Card(
//        modifier = modifier,
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight()
//                .background(Color.White)
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = product.image),
//                contentDescription = "Product Image",
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(RoundedCornerShape(8.dp))
//                    .background(color = Color(0xFFECECEC)),
//            )
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Column(
//                modifier = Modifier.fillMaxWidth(),
//                verticalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = product.name,
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    lineHeight = 16.sp,
//                    minLines = 1,
//                )
//                Text(
//                    text = product.description,
//                    color = Color.Gray,
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 14.sp,
//                    lineHeight = 14.sp,
//                    minLines = 1,
//                )
//                Text(
//                    text = "$${product.price.toInt()}",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 14.sp,
//                    lineHeight = 14.sp,
//                    minLines = 1,
//                )
//            }
//
//            IconButton(
//                onClick = { },
//                modifier = Modifier
//                    .size(30.dp)
//                    .clip(CircleShape)
//                    .align(Alignment.Bottom)
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.ShoppingCart,
//                    contentDescription = "Add to Cart",
//                    tint = Color.Red
//                )
//            }
//        }
//    }
//}
package vn.edu.hcmut.furniturestore.adapter.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import android.widget.Toast

@Composable
fun ARScreen() {
    // Get context for Toast messages
    val context = LocalContext.current

    var showCoachingOverlay by remember { mutableStateOf(true) }
    var showGestureOverlay by remember { mutableStateOf(false) }
    var showUiControls by remember { mutableStateOf(false) }
    var hasOverlayShown by remember { mutableStateOf(false) }
    var currentModel by remember { mutableStateOf("") }
    var showInfoPanel by remember { mutableStateOf(false) }

    // Add error handling state
    var isModelLoaded by remember { mutableStateOf(true) }

    // Find the selected product based on the current model name
    val selectedProduct by remember(currentModel) {
        derivedStateOf {
            products.find { it.modelName == currentModel }
        }
    }

    LaunchedEffect(Unit) {
        delay(3000)
        showCoachingOverlay = false
        showUiControls = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // AR View takes the full screen with error handling
        ARView(
            modelName = currentModel,
            onModelLoadSuccess = { isModelLoaded = true },
            onModelLoadError = { error ->
                isModelLoaded = false
                Toast.makeText(context, "Failed to load model: $error", Toast.LENGTH_LONG).show()
            }
        )

        // Coaching overlay
        AnimatedVisibility(
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

        // Gesture overlay
        AnimatedVisibility(
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

        // Fabric selector
        Box(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterEnd),
        ) {
            AnimatedVisibility(
                visible = showUiControls && isModelLoaded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FabricSelector(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(5.dp),
                    onClick = { fabricName ->
                        // Handle fabric selection
                        Toast.makeText(context, "Selected fabric: $fabricName", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        // Info floating action button
        AnimatedVisibility(
            visible = showUiControls && currentModel.isNotEmpty() && isModelLoaded,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            FloatingActionButton(
                onClick = { showInfoPanel = !showInfoPanel },
                shape = CircleShape,
                containerColor = Color.White,
                contentColor = Color(0xFF1976D2)
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Show Object Information"
                )
            }
        }

        // Product selector at bottom
        Box(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter),
        ) {
            AnimatedVisibility(
                visible = showUiControls,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                // Pass empty list if products is null to avoid crash
                val productItems = remember { products.ifEmpty { emptyList() } }

                ProductSelector(
                    products = productItems,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onClick = { modelName ->
                        try {
                            currentModel = modelName
                            if (!hasOverlayShown) {
                                showGestureOverlay = true
                            }
                            // Hide info panel when switching products
                            showInfoPanel = false
                        } catch (e: Exception) {
                            Toast.makeText(context, "Error loading model: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                )
            }
        }

        // Product Information Panel
        AnimatedVisibility(
            visible = showInfoPanel && selectedProduct != null,
            enter = slideInVertically(initialOffsetY = { -it }) + expandVertically() + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + shrinkVertically() + fadeOut(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp) // Add padding to position below status bar
        ) {
            selectedProduct?.let { product ->
                ObjectInfoPanel(product = product)
            }
        }
    }
}

@Composable
fun ObjectInfoPanel(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header with product name and image
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFECECEC))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = product.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2)
                    )

                    Text(
                        text = product.description,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "$${product.price.toInt()}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4CAF50)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Dimensions section
            Text(
                text = "Dimensions",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DimensionItem(label = "Width", value = "${product.width ?: 80} cm")
                DimensionItem(label = "Height", value = "${product.height ?: 120} cm")
                DimensionItem(label = "Length", value = "${product.depth ?: 60} cm")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Materials section
            Text(
                text = "Materials",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.material ?: "Wood, Metal, Fabric",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // AR Mode tips
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Tip: Pinch to resize, rotate with two fingers, or drag to reposition",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 14.sp,
                    color = Color(0xFF1976D2),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DimensionItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun FabricSelector(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    // Make sure fabrics list isn't empty
    val fabricItems = remember { fabrics.ifEmpty { emptyList() } }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(2.dp),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically,
        ),
    ) {
        items(fabricItems) { fabric ->
            Box(
                modifier = Modifier
                    .clickable { onClick(fabric.name) }
                    .size(30.dp)
            ) {
                Image(
                    modifier = Modifier.clip(CircleShape),
                    painter = painterResource(id = fabric.imageId),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun ProductSelector(
    products: List<Product>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(products) { product ->
            ProductView(
                modifier = Modifier.clickable {
                    if (product.modelName.isNotEmpty()) {
                        onClick(product.modelName)
                    }
                },
                product = product
            )
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
                modifier = Modifier.fillMaxWidth(0.7f),  // Limit width to prevent overlap
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    minLines = 1,
                    maxLines = 1,
                )
                Text(
                    text = product.description,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    minLines = 1,
                    maxLines = 1,
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
