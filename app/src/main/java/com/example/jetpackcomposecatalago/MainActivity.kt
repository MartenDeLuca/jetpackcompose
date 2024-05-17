package com.example.jetpackcomposecatalago

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecatalago.ui.theme.JetpackComposeCatalagoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jetpackcomposecatalago.ui.theme.CheckInfo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCatalagoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {

                    /*
                    var myText by rememberSaveable { mutableStateOf("") }
                    MyTextFieldOutlined(myText) { myText = it }

                    var enabled by rememberSaveable { mutableStateOf(true) }
                    MyButton(enabled) { enabled = false }

                    var name by rememberSaveable { mutableStateOf("Martin") }
                    MyRadioButtonList(name) {name = it}

                    var opciones = getOptions(listOf("Martin", "Guido", "Mario"));
                    Column() {
                        MyTryCheckBox()
                        opciones.forEach {
                            MyCheckBoxWithTextAdvanced(checkInfo = it)
                        }
                    }*/
                    var show by rememberSaveable { mutableStateOf(false) }


                    MySimpleDialogCustom(show = show, onConfirm = { Log.i("Martin", "Click alert") }, onDismiss = { show = false })
                    
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeCatalagoTheme {
        MyBadge()
    }
}

@Composable
fun MyDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Red)
    )
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = Color.Red,
            containerColor = Color.Blue
        ),
        border = BorderStroke(5.dp, Color.Yellow)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Martin")
            Text("Mario")
            Text("Guido")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadge() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        BadgedBox(
            badge = {
                Badge(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
                { Text("411") }
            }
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "")
        }
    }
}

@Composable
fun MySlider() {
    var sliderPositions by rememberSaveable { mutableStateOf(0f) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Slider(value = sliderPositions, onValueChange = { sliderPositions = it })
        Text(text = sliderPositions.toString())
    }
}

@Composable
fun MySliderAdvanced() {
    var sliderPositions by rememberSaveable { mutableStateOf(0f) }
    var completePositions by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Slider(
            value = sliderPositions,
            onValueChange = { sliderPositions = it },
            onValueChangeFinished = { completePositions = sliderPositions.toString() },
            valueRange = 0f..10f,
            steps = 9,
            enabled = true
        )
        Text(text = completePositions)
    }
}

@Composable
fun MyTextField() {
    var myText by rememberSaveable { mutableStateOf("Aris") }
    TextField(value = myText, onValueChange = { myText = it })
}

@Composable
fun MyTextFieldAdvance() {
    var myText by rememberSaveable { mutableStateOf("") }
    TextField(value = myText, onValueChange = {
        myText = if (it.contains("a")) {
            it.replace("a", "")
        } else {
            it
        }
    }, label = { Text("Introduce tu nombre") })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRangeSlider(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var currenteRange by rememberSaveable { mutableStateOf(0f..10f) }

        RangeSlider(value = currenteRange, onValueChange = {currenteRange = it}, valueRange = 0f..10f, steps = 9)
        Text(text = "Valor inferior ${currenteRange.start}")
        Text(text = "Valor superior ${currenteRange.endInclusive}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldOutlined(myText: String, onValueChange: (String) -> Unit) {
    Column() {
        OutlinedTextField(
            value = myText,
            onValueChange = { onValueChange(it) },
            label = { Text(text = "Introduce tu nombre") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Magenta, unfocusedBorderColor = Color.Blue
            )
        )
    }
}

@Composable
fun MyAlertDialog(show: Boolean, onConfirm: () -> Unit, onDismiss: () -> Unit){
    if(show){
        AlertDialog(
            icon = {
                Icon(Icons.Default.Call, contentDescription = "Example Icon")
            },
            title = {
                Text(text = "Prueba")
            },
            text = {
                Text(text = "Esto es una alerta")
            },
            onDismissRequest = {
                onDismiss()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}


@Composable
fun MySimpleDialogCustom(show: Boolean, onConfirm: () -> Unit, onDismiss: () -> Unit){
    Dialog(onDismissRequest = { onDismiss() }, properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {
        Column(modifier = Modifier
            .background(Color.Red)
            .padding(24.dp)
            .fillMaxSize()) {
            Text(text = "Esto es un ejemplo")
        }
    }
}

@Composable
fun MyButton(enabled: Boolean, onClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { onClick() }, enabled = enabled, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta, contentColor = Color.Blue
            ), border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Click")
        }

        OutlinedButton(
            enabled = enabled, onClick = { onClick() }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.Blue,
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.White
            ), modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "Click")
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Hola")
        }
    }
}

@Composable
fun MyImage() {
    //Para agregar imagenes: res/drawable
    //contentDescription es como el alt
    //alpha es la opacidad
    //clip nos permite darle el redondeado que queramos CircleShape para hacerlo un circulo o RoundedCornerShape(25f)
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Tesys",
        alpha = 0.5f,
        modifier = Modifier
            .clip(CircleShape)
            .border(0.7.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyIcon() {
    //con tint cambiamos el color
    //https://fonts.google.com/icons

    Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.Red)
}

@Composable
fun MyProgressBar() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Red,
                trackColor = Color.Green
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Click")
        }
    }
}

@Composable
fun MyProgressBarAdvanced() {
    var progressBar by rememberSaveable { mutableStateOf(0f) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = progressBar)

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                if (progressBar < 1f) {
                    progressBar += 0.1f
                }
            }) {
                Text(text = "Aumentar")
            }

            Button(onClick = {
                if (progressBar > 0f) {
                    progressBar -= 0.1f
                }
            }) {
                Text(text = "Descontar")
            }
        }
    }
}

@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(false) }
    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Magenta,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Cyan,
            disabledCheckedTrackColor = Color.Yellow,
            disabledCheckedThumbColor = Color.Yellow

        )
    )
}

@Composable
fun MyDropdownMenu() {
    var selectedText by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val desserts = listOf("Helado", "Chocolate", "Cafe", "Fruta")
    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true })
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {


            desserts.forEach { dessert ->
                DropdownMenuItem(text = { Text(text = dessert) }, onClick = {
                    expanded = false
                    selectedText = dessert
                })
            }
        }
    }

}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Row() {
            RadioButton(selected = name == "Martin", onClick = { onItemSelected("Martin") })

            RadioButton(selected = name == "Mario", onClick = { onItemSelected("Mario") })

            RadioButton(selected = name == "Guido", onClick = { onItemSelected("Guido") })
        }
    }
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxSize()) {
        var state by rememberSaveable { mutableStateOf(false) }
        RadioButton(
            selected = state, onClick = { }, enabled = true, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Blue,
                disabledSelectedColor = Color.Yellow
            )
        )
        Text("Ejemplo")
    }
}

@Composable
fun MyTryCheckBox() {
    var state by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    TriStateCheckbox(state = state, onClick = {
        state = when (state) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun getOptions(titulos: List<String>): List<CheckInfo> {
    return titulos.map {
        var state by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = state,
            onCheckedChange = { myNewStatus -> state = myNewStatus })
    }
}

@Composable
fun MyCheckBoxWithTextAdvanced(checkInfo: CheckInfo) {
    Row(Modifier.padding(10.dp)) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title, modifier = Modifier.align(CenterVertically))
    }
}

@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable { mutableStateOf(true) }
    Row() {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(true) }
    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(checkedColor = Color.Red, uncheckedColor = Color.Blue)
    )
}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text("Esto es un ejemplo")
        Text("Esto es un ejemplo", color = Color.Blue)
        Text("Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text("Esto es un ejemplo", fontFamily = FontFamily.Cursive)
        Text("Esto es un ejemplo", textDecoration = TextDecoration.LineThrough)
        Text("Esto es un ejemplo", textDecoration = TextDecoration.Underline)
        Text(
            "Esto es un ejemplo", textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.LineThrough, TextDecoration.Underline
                )
            )
        )
        Text("Esto es un ejemplo", fontSize = 30.sp)
        Text("Esto es un ejemplo", style = TextStyle(textDecoration = TextDecoration.Underline))
    }
}

@Composable
fun MyStateExample() {
    var counter by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter += 1 }) {
            Text(text = "Pulsar")
        }

        Text("He sido pulsado ${counter} veces")
    }
}

@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .background(Color.Cyan)
                .verticalScroll(
                    rememberScrollState()
                ), contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "Hola martin")
        }
    }
}

@Composable
fun MyRow() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )

    }
}

@Composable
fun MyColumn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Hola",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Yellow)
        )
        Text(
            text = "Hola", modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        )

    }
}

@Composable
fun MySpacer(height: Int) {
    Spacer(
        modifier = Modifier
            .width(0.dp)
            .height(height.dp)
    )
}

@Composable
fun MyComplexLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Yellow),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Ejemplo arriba")
        }
        MySpacer(20)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Red)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Ejemplo izquierda")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Cyan)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Ejemplo derecha")
            }
        }
        MySpacer(20)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Blue),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "Ejemplo fin")
        }

    }
}
