package fureverlove.ucb

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// Clase de datos para representar un plan
data class Plan(val title: String)

// ViewModel que maneja el estado de los planes móviles
class MobilePlansViewModel : ViewModel() {

    // Lista de planes disponibles
    val plans: List<Plan> = listOf(
        Plan("Plan Básico"),
        Plan("Plan Plus"),
        Plan("Plan Ultra")
    )

    // Estado del índice del plan seleccionado
    private val _currentPlanIndex = mutableStateOf(0)
    val currentPlanIndex: State<Int> get() = _currentPlanIndex

    // Función para cambiar el plan actual
    fun selectPlan(index: Int) {
        if (index in plans.indices) {
            _currentPlanIndex.value = index
        }
    }

    // Simula el envío de una solicitud de SIM
    fun submitSimRequest(phone: String, lat: Double, lng: Double) {
        // Aquí puedes agregar la lógica para enviar los datos a un servidor o almacenarlos
        println("Solicitud enviada para $phone en ubicación ($lat, $lng)")
    }
}

