package sa.factory.app1290123.data

import sa.factory.app1290123.model.ProjectRiskMetric
import sa.factory.app1290123.model.DataIntegrityAudit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ProjectRiskMetricRepository {
    fun getAvailableCatalog(): Flow<List<DataIntegrityAudit>>
    fun getActiveRequest(): Flow<ProjectRiskMetric?>
    suspend fun submitRequest(request: ProjectRiskMetric): Result<String>
}

class InMemoryProjectRiskMetricRepository : ProjectRiskMetricRepository {
    private val _catalog = MutableStateFlow<List<DataIntegrityAudit>>(listOf(
        DataIntegrityAudit("1", "حزمة التدقيق الفوري وتطابق البيانات", "مطابقة آلية لملفات المشاريع مع كشف فجوات الجودة وتحديث سجل المخاطر", 5000L, "verified"),
        DataIntegrityAudit("2", "اشتراك المحرك اللحظي لمراقبة الكفاءة", "مزامنة سحابية مستمرة مع تحليل الفاعلية وإرسال تنبيهات الانحراف الفورية", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<ProjectRiskMetric?>(null)

    override fun getAvailableCatalog(): Flow<List<DataIntegrityAudit>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<ProjectRiskMetric?> = _active.asStateFlow()

    override suspend fun submitRequest(request: ProjectRiskMetric): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
