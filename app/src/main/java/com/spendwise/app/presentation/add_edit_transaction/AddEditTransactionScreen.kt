package com.spendwise.app.presentation.add_edit_transaction

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.spendwise.app.domain.model.Category
import com.spendwise.app.domain.model.Currency
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTransactionScreen(
    navController: NavController,
    viewModel: AddEditTransactionViewModel = hiltViewModel()
) {
    val titleState = viewModel.transactionTitle.value
    val amountState = viewModel.transactionAmount.value
    val selectedCategory = viewModel.selectedCategory.value
    val selectedCurrency = viewModel.selectedCurrency.value
    val imagePath = viewModel.transactionImage.value
    val snackbarHostState = remember { SnackbarHostState() }
    
    var showCurrencyPicker by remember { mutableStateOf(false) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            viewModel.onEvent(AddEditTransactionEvent.EnteredImage(uri))
        }
    )

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditTransactionViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
                is AddEditTransactionViewModel.UiEvent.SaveTransaction -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "记一笔") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTransactionEvent.SaveTransaction)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Save transaction")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = titleState,
                onValueChange = {
                    viewModel.onEvent(AddEditTransactionEvent.EnteredTitle(it))
                },
                label = { Text("标题") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = amountState,
                    onValueChange = {
                        viewModel.onEvent(AddEditTransactionEvent.EnteredAmount(it))
                    },
                    label = { Text("金额") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(12.dp))
                Surface(
                    onClick = { showCurrencyPicker = true },
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.height(56.dp).padding(top = 8.dp)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "${selectedCurrency.code} ${selectedCurrency.symbol}", fontWeight = FontWeight.Bold)
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(text = "选择分类", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(Category.defaultCategories) { category ->
                    CategoryItem(
                        category = category,
                        isSelected = category.name == selectedCategory.name,
                        onClick = {
                            viewModel.onEvent(AddEditTransactionEvent.EnteredCategory(category.name))
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "附件图片", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))

            if (imagePath != null) {
                Box(modifier = Modifier.size(120.dp)) {
                    AsyncImage(
                        model = imagePath,
                        contentDescription = "Attachment",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = { viewModel.onEvent(AddEditTransactionEvent.EnteredImage(null)) },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Remove photo",
                            tint = Color.White,
                            modifier = Modifier.background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                        )
                    }
                }
            } else {
                Surface(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.size(120.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Add, contentDescription = null)
                            Text(text = "上传图片", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
    
    if (showCurrencyPicker) {
        CurrencyPickerDialog(
            onDismiss = { showCurrencyPicker = false },
            onConfirm = { currency ->
                viewModel.onEvent(AddEditTransactionEvent.ChangedCurrency(currency))
                showCurrencyPicker = false
            }
        )
    }
}

@Composable
fun CurrencyPickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (Currency) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("选择币种") },
        text = {
            Column {
                Currency.values().forEach { currency ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onConfirm(currency) }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = currency.code, fontWeight = FontWeight.Medium)
                        Text(text = currency.symbol, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("关闭")
            }
        }
    )
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(56.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = category.icon, fontSize = 24.sp)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category.name,
            fontSize = 12.sp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
