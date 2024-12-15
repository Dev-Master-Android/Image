package com.example.image

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterBuilder() {
    var character by remember { mutableStateOf(Character()) }
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Конструктор персонажа") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu Icon"
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Сбросить") },
                            onClick = {
                                character = Character()
                                showMenu = false
                            })
                        DropdownMenuItem(
                            text = { Text("Случайный образ") },
                            onClick = {
                                character = randomCharacter()
                                showMenu = false
                            })
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Цвет волос")
                Spacer(modifier = Modifier.size(10.dp))
                ExposedDropdownMenu(
                    label = "Цвет волос",
                    options = HairColor.values().toList(),
                    selectedOption = character.hairColor,
                    onOptionSelected = { character = character.copy(hairColor = it) }
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text("Прическа")
                Spacer(modifier = Modifier.size(10.dp))
                ExposedDropdownMenu(
                    label = "Прическа",
                    options = Hairstyle.values().toList(),
                    selectedOption = character.hairstyle,
                    onOptionSelected = { character = character.copy(hairstyle = it) }
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text("Борода")
                Spacer(modifier = Modifier.size(10.dp))
                ExposedDropdownMenu(
                    label = "Борода",
                    options = Beard.values().toList(),
                    selectedOption = character.beard,
                    onOptionSelected = { character = character.copy(beard = it) }
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text("Аксессуары")
                Spacer(modifier = Modifier.size(10.dp))
                ExposedDropdownMenu(
                    label = "Аксессуары",
                    options = Accessories.values().toList(),
                    selectedOption = character.accessories,
                    onOptionSelected = { character = character.copy(accessories = it) }
                )
                Spacer(modifier = Modifier.size(20.dp))
                CharacterCanvas(character)
                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun CharacterCanvas(character: Character) {
    Box(
        modifier = Modifier.offset(x = 0.dp, y = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_character),
            contentDescription = "Character",
            modifier = Modifier.size(200.dp)
        )
        Image(
            painter = painterResource(
                id = getResourceByNameHair(
                    character.hairColor,
                    character.hairstyle
                )
            ),
            contentDescription = "Character",
            modifier = Modifier
                .size(90.dp)
                .offset(x = 55.dp, y = (-20).dp)
        )
        Image(
            painter = painterResource(id = getResourceByBeard(character.beard)),
            contentDescription = "Character",
            modifier = Modifier
                .size(94.dp)
                .offset(x = 53.dp, y = (52).dp)
        )
        Image(
            painter = painterResource(id = getResourceByGlasses(character.accessories)),
            contentDescription = "Character",
            modifier = Modifier
                .size(90.dp)
                .offset(x = 55.dp, y = (20).dp)
        )
    }
}

@DrawableRes
fun getResourceByNameHair(hairColor: HairColor, hairstyle: Hairstyle): Int {
    return when ("${hairColor.name.lowercase()}_${hairstyle.name.lowercase()}") {
        "black_short" -> R.drawable.black_short
        "brown_short" -> R.drawable.brown_short
        "blonde_short" -> R.drawable.blonde_short
        "red_short" -> R.drawable.red_short
        "black_medium" -> R.drawable.black_medium
        "brown_medium" -> R.drawable.brown_medium
        "blonde_medium" -> R.drawable.blonde_medium
        "red_medium" -> R.drawable.red_medium
        "black_long" -> R.drawable.black_long
        "brown_long" -> R.drawable.brown_long
        "blonde_long" -> R.drawable.blonde_long
        "red_long" -> R.drawable.red_long
        else -> R.drawable.default_hair // Значение по умолчанию
    }
}

@DrawableRes
fun getResourceByBeard(beard: Beard): Int {
    return when (beard) {
        Beard.None -> R.drawable.beard_none
        Beard.Mustache -> R.drawable.beard_mustache
        Beard.FullBeard -> R.drawable.beard_fullbeard
        else -> R.drawable.default_beard // Значение по умолчанию
    }
}

@DrawableRes
fun getResourceByGlasses(accessories: Accessories): Int {
    return when (accessories) {
        Accessories.Glasses -> R.drawable.accessory_glasses
        else -> R.drawable.default_accessory // Значение по умолчанию
    }
}

fun randomCharacter(): Character {
    return Character(
        hairColor = HairColor.values().random(),
        hairstyle = Hairstyle.values().random(),
        beard = Beard.values().random(),
        accessories = Accessories.values().random()
    )
}
