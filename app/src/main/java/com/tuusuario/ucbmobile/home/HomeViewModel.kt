package com.tuusuario.ucbmobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    // Estado para planes móviles
    private val _currentPlanIndex = MutableStateFlow(0)
    val currentPlanIndex: StateFlow<Int> = _currentPlanIndex.asStateFlow()

    val plans = listOf(
        Plan(
            title = "Plan FLEX 5",
            price = 279.0,
            discountedPrice = 199.0,
            dataGB = 5,
            description = "Llamadas y SMS limitadas",
            benefits = listOf(
                "Hotspot: Comparte tus datos",
                "Redes Sociales limitadas incluidas",
                "Personaliza tu plan con más apps",
                "Compromiso CO2 negativo"
            )
        ),
        Plan(
            title = "Plan FLEX 8",
            price = 370.0,
            discountedPrice = 299.0,
            dataGB = 8,
            description = "Llamadas y SMS ilimitados",
            benefits = listOf(
                "Hotspot incluido",
                "Redes Sociales ilimitadas",
                "Música y video sin consumo",
                "Compromiso CO2 negativo"
            )
        )
    )

    fun navigateToNextPlan() {
        _currentPlanIndex.update { (it + 1) % plans.size }
    }

    fun navigateToPreviousPlan() {
        _currentPlanIndex.update { (it - 1 + plans.size) % plans.size }
    }

    fun submitSimRequest(phone: String, address: String, reference: String) {
        viewModelScope.launch {
            // Lógica para enviar la solicitud
            println("Solicitud de SIM para $phone en $address ($reference)")
        }
    }
}

data class Plan(
    val title: String,
    val price: Double,
    val discountedPrice: Double,
    val dataGB: Int,
    val description: String,
    val benefits: List<String>
)