package sa.factory.app1290123.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sa.factory.app1290123.model.DataIntegrityAudit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectRiskMetricHomeScreen(
    viewModel: ProjectRiskMetricViewModel,
    onSelectItem: (DataIntegrityAudit) -> Unit,
    onQuickCheckout: () -> Unit
) {
    val catalog by viewModel.catalog.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("منصة راصد لمخاطر ومؤشرات المشاريع", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("حوكمة المشاريع ومزامنة البيانات الم", fontSize = 12.sp, color = Color.Gray)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                // Bespoke Vector Graphic Header (>= 8 Paths branched on archetype)
                BespokeVectorIllustration(
                    archetype = "service_stepper",
                    screenIdx = 1,
                    modifier = Modifier.fillMaxWidth().height(160.dp)
                )
            }

            
            item {
                Text("مراحل تنفيذ المشروع والاعتماد الفني:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("• المرحلة 1: التدقيق الهندسي والمعاينة الميدانية", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                        Text("• المرحلة 2: إبرام العقد وتوثيق المتطلبات وسندات القبض", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                        Text("• المرحلة 3: التسليم النهائي والضمان التشغيلي المعتمد", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = onQuickCheckout, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp)) {
                            Text("طلب استشارة هندسية فورية", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        

            item {
                Text("قائمة الخدمات المعتمدة للتخصيص:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            items(catalog) { item ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text(item.description, color = Color.Gray, fontSize = 12.sp)
                        }
                        Button(
                            onClick = { onSelectItem(item) },
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("${item.priceMinorUnits / 100} SAR")
                        }
                    }
                }
            }
        }
    }
}
