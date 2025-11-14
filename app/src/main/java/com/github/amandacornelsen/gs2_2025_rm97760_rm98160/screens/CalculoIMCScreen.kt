package com.github.amandacornelsen.gs2_2025_rm97760_rm98160.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.pow

@Composable
fun CalculoIMCScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var nome by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultadoIMC by remember { mutableStateOf<Double?>(null) }
    var classificacaoIMC by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    fun calcularImcSimples(peso: Double, altura: Double): Double {
        return peso / (altura/100) .pow(2.0)
    }

    fun classificarImcSimples(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Peso Ideal"
            imc < 30.0 -> "Levemente acima do peso"
            imc < 35.0 -> "Obesidade Grau I"
            imc < 40.0 -> "Obesidade Grau II"
            else -> "Obesidade Grau III"
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF7FBE82))
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CALCULE SEU IMC",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Seu nome") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Peso (kg)") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Altura (cm)") }, // Solicite em METROS!
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val nomeTrim = nome.trim()
                    val pesoNum = peso.trim().replace(",", ".").toDoubleOrNull()
                    val alturaNum = altura.trim().replace(",", ".").toDoubleOrNull()

                    when {
                        nomeTrim.isEmpty() -> {
                            error = "Informe o nome."
                            resultadoIMC = null
                            classificacaoIMC = null
                        }
                        pesoNum == null || alturaNum == null -> {
                            error = "Preencha todos os campos corretamente."
                            resultadoIMC = null
                            classificacaoIMC = null
                        }
                        alturaNum == 0.0 -> {
                            error = "Altura não pode ser zero."
                            resultadoIMC = null
                            classificacaoIMC = null
                        }
                        else -> {
                            resultadoIMC = calcularImcSimples(pesoNum, alturaNum)
                            classificacaoIMC = classificarImcSimples(resultadoIMC!!)
                            error = null
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 18.sp,
                    color = Color(0xFFED145B),
                    fontWeight = FontWeight.Bold
                )
            }
            if (resultadoIMC != null && classificacaoIMC != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${nome.trim()}, seu IMC é: ${"%.2f".format(resultadoIMC)}",
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Text(
                    text = classificacaoIMC ?: "",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            if (error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error!!,
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate("menu") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .width(140.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "Voltar",
                    fontSize = 18.sp,
                    color = Color(0xFFED145B),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}