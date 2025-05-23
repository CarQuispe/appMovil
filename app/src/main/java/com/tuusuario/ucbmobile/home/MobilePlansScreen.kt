package com.tuusuario.ucbmobile

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fureverlove.ucb.SocialMediaRow

import androidx.compose.material.icons.filled.ArrowForward

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobilePlansScreen(
    viewModel: HomeViewModel = viewModel(),
    onBack: () -> Unit = {},
    onWhatsAppClick: () -> Unit = {}
) {
    var showSimForm by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val currentPlanIndex by viewModel.currentPlanIndex.collectAsState()
    val currentPlan = viewModel.plans[currentPlanIndex]

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Nuestros planes móviles",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = currentPlan.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = currentPlan.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                PriceDisplay(
                    price = currentPlan.price,
                    discountedPrice = currentPlan.discountedPrice,
                    dataGB = currentPlan.dataGB
                )
            }

            item {
                BenefitsList(benefits = currentPlan.benefits)
            }

            item {
                PlanNavigationButtons(
                    onPrevious = { viewModel.navigateToPreviousPlan() },
                    onNext = { viewModel.navigateToNextPlan() }
                )
            }

            item {
                Button(
                    onClick = onWhatsAppClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Contactar por WhatsApp", color = Color.White)
                }
            }

            item {
                Button(
                    onClick = { showSimForm = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Solicitar envío de SIM", color = Color.White)
                }
            }

            item {
                SocialMediaRow()
            }
        }
    }

    if (showSimForm) {
        SimRequestForm(
            onBack = { showSimForm = false },
            onSubmit = { phone, latitude, longitude ->
                Toast.makeText(context, "Solicitud enviada para $phone en Lat: $latitude, Long: $longitude", Toast.LENGTH_SHORT).show()
                showSimForm = false
            }
        )
    }
}

@Composable
fun PriceDisplay(price: Double, discountedPrice: Double, dataGB: Int) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$${discountedPrice}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$$price",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            )
        }
        Text(
            text = "$dataGB GB de datos",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun BenefitsList(benefits: List<String>) {
    Column {
        Text(
            text = "Beneficios:",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            benefits.forEach { benefit ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = benefit)
                }
            }
        }
    }
}

@Composable
fun PlanNavigationButtons(onPrevious: () -> Unit, onNext: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), // altura fija para centrar mejor
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPrevious) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Plan anterior",
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(48.dp)
                )
            }

            IconButton(onClick = onNext) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Siguiente plan",
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

