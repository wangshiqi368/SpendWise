package com.spendwise.app.presentation.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spendwise.app.domain.model.CategoryStat
import com.spendwise.app.domain.model.CurrencyStat
import com.spendwise.app.presentation.statistics.components.BarChart
import com.spendwise.app.presentation.statistics.components.PieChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(
    navController: NavController,
    viewModel: StatisticsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val tabs = listOf("按类别", "按币种")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "支出分析", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        val hasData = if (state.selectedTab == StatType.CATEGORY) state.categoryStats.isNotEmpty() else state.currencyStats.isNotEmpty()

        if (!hasData) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "暂无消费记录，无法生成分析报告", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            Column(modifier = Modifier.padding(padding)) {
                TabRow(selectedTabIndex = state.selectedTab.ordinal) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = state.selectedTab.ordinal == index,
                            onClick = { viewModel.onEvent(StatisticsEvent.OnTabSelected(StatType.values()[index])) },
                            text = { Text(title) }
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "本月总计支出 (折合)", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(
                                text = "¥${String.format("%.2f", state.totalSpending)}",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Box(modifier = Modifier.height(280.dp)) {
                                if (state.selectedTab == StatType.CATEGORY) {
                                    PieChart(stats = state.categoryStats)
                                } else {
                                    BarChart(stats = state.currencyStats)
                                }
                            }
                        }
                    }

                    if (state.selectedTab == StatType.CATEGORY) {
                        items(state.categoryStats) { stat ->
                            CategoryStatItem(stat)
                        }
                    } else {
                        items(state.currencyStats) { stat ->
                            CurrencyStatItem(stat)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryStatItem(stat: CategoryStat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(stat.color, CircleShape)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(text = stat.categoryIcon, fontSize = 20.sp)
        
        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = stat.categoryName, fontWeight = FontWeight.SemiBold)
            Text(text = "${(stat.percentage * 100).toInt()}%", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        
        Text(
            text = "¥${String.format("%.2f", stat.totalAmount)}",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CurrencyStatItem(stat: CurrencyStat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(stat.color, CircleShape)
        )
        
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = "${stat.currency.code} (${stat.currency.symbol})", fontWeight = FontWeight.SemiBold)
            Text(text = "${(stat.percentage * 100).toInt()}%", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        
        Text(
            text = "¥${String.format("%.2f", stat.totalAmountInCny)}",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
