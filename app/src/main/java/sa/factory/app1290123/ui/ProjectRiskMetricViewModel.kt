package sa.factory.app1290123.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290123.data.ProjectRiskMetricRepository
import sa.factory.app1290123.model.ProjectRiskMetric
import sa.factory.app1290123.model.DataIntegrityAudit
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProjectRiskMetricViewModel(
    private val repository: ProjectRiskMetricRepository
) : ViewModel() {
    val catalog: StateFlow<List<DataIntegrityAudit>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<ProjectRiskMetric?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: ProjectRiskMetric) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
