package hoangloc.mytipapp

import android.os.Bundle
import android.support.v4.os.IResultReceiver2.Default
import android.util.Log
import android.view.Surface
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoangloc.mytipapp.components.InputField
import hoangloc.mytipapp.ui.theme.MyTipAppTheme
import hoangloc.mytipapp.widgets.RoundIconButton

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Myapp{
                MainContent()
            }
        }
    }
}

@Composable
fun Myapp(content: @Composable () -> Unit) {
    MyTipAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }

    }
}

@Preview
@Composable
fun TopHeadler(totalPerPerson: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(15.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = Color(0xFF62BE87),
        border = BorderStroke(width = 2.dp, color = Color.LightGray),
        ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val total = "%.2f".format(totalPerPerson)
            Text(text = "Total Per Person",
                style = MaterialTheme.typography.headlineSmall)
            Text(text = "$$total",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold)
        }

    }
}

@Preview
@Composable
fun MainContent() {
        BillForm(){
        billAmt ->
        Log.d("AMT","MainContent: ${billAmt.toInt() * 100}")
    }
}

@Composable
private fun BillForm(
    modifier: Modifier = Modifier,
    onvaluechanged: (String) -> Unit = {},
) {
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }

    val tipPercentage = (sliderPositionState.value * 100).toInt()

    val splitByState = remember {
        mutableStateOf(1)
    }

    val tipAmountState = remember {
        mutableStateOf(0.0)
    }



    Column {//create skeleton for header and main content
        TopHeadler()

        Surface(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {
            Column(
                modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                InputField(
                    valueState = totalBillState,
                    labelId = "Enter Bill",
                    enabled = true,
                    isSingleLine = true,
                    onAction = KeyboardActions() {
                        if (!validState) return@KeyboardActions
                        onvaluechanged(totalBillState.value.trim())
                        keyboardController?.hide()
                    }
                )
//                SplitRow(visible = validState) // Show only when bill is valid
//
//                TipRow(
//                    tipAmount = calculateTip(
//                        sliderPositionState.value,
//                        totalBillState.value.toFloatOrNull() ?: 0f
//                    )
//                )
//
//                TipSlider(sliderPositionState = sliderPositionState)
                if (validState) {
                Row (
                    modifier = Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                ){
                    Text(text = "Split",
                        modifier = Modifier.align(
                            alignment = Alignment.CenterVertically
                        ))

                    Spacer(modifier = Modifier.width(120.dp))

                    Row (
                        modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = {
                                splitByState.value =
                                    if (splitByState.value > 1) splitByState.value - 1
                                else 1
                            })

                        Text(text = "${splitByState.value}",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp))

                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = {
                                splitByState.value = splitByState.value + 1
                            })
                    }
                }

            //TipRow
            Row(
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp)
            ){
                Text(text = "Tip",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)                            )
                Spacer(modifier = Modifier.width(200.dp))
                Text(text = "$${tipAmountState.value}",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)


                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${tipPercentage} %")

                Spacer(modifier = Modifier.height(14.dp))

                //Slider
                Slider(value = sliderPositionState.value,
                    onValueChange = {
                        newVal ->
                        sliderPositionState.value = newVal
                        tipAmountState.value = calculateTip(
                            totalBill = totalBillState.value.toDouble(),
                            tipPercentage = tipPercentage   )
                    },
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    onValueChangeFinished = {
                        //Log.d("Finished","${sliderPositionState.value}")
                    }
                )
            }
            } else {
                Box() {}
            }
            }
        }
    }
}

fun calculateTip(totalBill: Double,
                 tipPercentage: Int): Double {
 return if (totalBill > 1 &&
            totalBill.toString().isNotEmpty())
     (totalBill * tipPercentage) / 100
    else 0.0
}

@Composable
fun BillForm(modifier: Modifier = Modifier) {
}

//function for split
//@Composable
//fun SplitRow(visible: Boolean) {
//    if (visible) {
//        Row(
//            modifier = Modifier.padding(3.dp),
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Text(text = "Split", modifier = Modifier.align(Alignment.CenterVertically))
//
//            Spacer(modifier = Modifier.width(120.dp))
//
//        }
//    }
//}
//
//@Composable
//fun TipRow(tipAmount: Float) {
//    Row(
//        modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp)
//    ) {
//        Text(text = "Tip", modifier = Modifier.align(Alignment.CenterVertically))
//        Spacer(modifier = Modifier.width(200.dp))
//        Text(text = "$tipAmount",
//        modifier = Modifier.align(Alignment.CenterVertically))
//    }
//}
//
//@Composable
//fun TipSlider(sliderPositionState: MutableState<Float>) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text
//        = "${sliderPositionState.value.toInt()}%")
//        Spacer(modifier = Modifier.height(14.dp))
//        Slider(
//            value = sliderPositionState.value,
//            valueRange = 0f..100f,
//            onValueChange = { newVal ->
//                sliderPositionState.value = newVal
//            },
//            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
//            steps = 15
//        )
//    }
//}
//
//fun calculateTip(tipPercent: Float, billAmount: Float): Float {
  //  return billAmount * (tipPercent / 100)
