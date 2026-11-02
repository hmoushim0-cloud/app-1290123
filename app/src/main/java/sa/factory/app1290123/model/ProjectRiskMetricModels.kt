package sa.factory.app1290123.model

import kotlinx.serialization.Serializable

@Serializable
data class DataIntegrityAudit(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class ProjectRiskMetric(
    val requestId: String,
    val items: List<DataIntegrityAudit>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: ProjectRiskMetricStatus = ProjectRiskMetricStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class ProjectRiskMetricStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
