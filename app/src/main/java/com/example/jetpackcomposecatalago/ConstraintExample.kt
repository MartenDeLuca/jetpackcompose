package com.example.jetpackcomposecatalago

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintExample1() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow, boxMagenta, boxGreen) = createRefs()

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                top.linkTo(boxRed.bottom)
                end.linkTo(boxRed.start)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxRed.bottom)
                start.linkTo(boxRed.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                bottom.linkTo(boxRed.top)
                end.linkTo(boxRed.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                bottom.linkTo(boxRed.top)
                start.linkTo(boxRed.end)
            })
    }
}

@Composable
fun ConstraintExampleGuide() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        //creamos una linea invisible desde la parte arriba y lo mejor es definirlo en porcentaje
        //el objeto empezara al 10% del tamaño
        val topGuide = createGuidelineFromTop(0.1f)
        //creamos una linea invisible desde la parte izquierda
        val startGuide = createGuidelineFromStart(0.25f)
        val boxRed = createRef()

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            })
    }
}

@Composable
fun ConstraintBarrier() {

    ConstraintLayout() {
        val (boxRed, boxBlue, boxYellow) = createRefs()

        //creo una especie de jaula y ningun elemeneto lo puede tocar
        //Ademas le definimos a quien es la barrera
        val barrier = createEndBarrier(boxRed, boxBlue)

        Box(modifier = Modifier
            .size(225.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(boxBlue.bottom)
                start.linkTo(parent.start, margin = 32.dp)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                start.linkTo(parent.start, margin = 16.dp)
            })


        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(barrier)
            })
    }
}

@Composable
fun ConstraintChainHorizontalExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow) = createRefs()


        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(parent.start)
                end.linkTo(boxBlue.start)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                start.linkTo(boxRed.end)
                end.linkTo(boxYellow.start)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(boxBlue.end)
                end.linkTo(parent.end)
            })

        //Agrupamos los objetos se puede hacer horizontal o vertical
        //Hay tres estilos:
        //1) ChainStyle.Packed = Todos compactados
        //2) ChainStyle.Spread = Valor por defecto, separados todos con el mismo porcentaje
        //3) ChainStyle.SpreadInside = Separarlo lo mas entre ellos
        createHorizontalChain(boxRed, boxBlue, boxYellow, chainStyle = ChainStyle.Packed)
    }
}

@Preview
@Composable
fun ConstraintChainVerticalExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow) = createRefs()

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                bottom.linkTo(boxBlue.top)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxRed.bottom)
                bottom.linkTo(boxYellow.top)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                top.linkTo(boxBlue.bottom)
                bottom.linkTo(parent.bottom)
            })

        //Agrupamos los objetos se puede hacer horizontal o vertical
        //Hay tres estilos:
        //1) ChainStyle.Packed = Todos compactados
        //2) ChainStyle.Spread = Valor por defecto, separados todos con el mismo porcentaje
        //3) ChainStyle.SpreadInside = Separarlo lo mas entre ellos
        createVerticalChain(boxRed, boxBlue, boxYellow, chainStyle = ChainStyle.SpreadInside)
    }
}