package xyz.borsay.tixtactoe.run

import java.util.*
import kotlin.collections.ArrayList


var theSquare = arrayListOf<ArrayList<String>>()

fun main(){
    var itsTheComputersTurn = Random().nextInt(0, 2 ) == 0

    if(itsTheComputersTurn)
        println("Computer goes first")
    else
        println("You start the game!")

    createTheSquareBlanks()
    printTheBoard()

    for(i in 1.. 9){
        if(itsTheComputersTurn) {
            computersChoice()
            println("Computer has Entered")
        }else
            enterUserChoice()

        printTheBoard()
        println("***************************************************\n")

        if( isGameOver(itsTheComputersTurn)){
            println("${ if(itsTheComputersTurn) "The Computer (O)" else "You (X) " } has won the game")
            break
        }

        itsTheComputersTurn = !itsTheComputersTurn
    }

}

fun isGameOver(itsTheComputersTurn: Boolean): Boolean{

    val variableTest = if(itsTheComputersTurn) "O" else "X"

    for(i in 0..2){
        if((theSquare[i][0] == variableTest) && (theSquare[i][1] == variableTest) && (theSquare[i][2] == variableTest))
            return true
        else if((theSquare[0][i] == variableTest) && (theSquare[1][i] == variableTest) && (theSquare[2][i] == variableTest))
            return true
    }

    if((theSquare[0][0] == variableTest) && (theSquare[1][1] == variableTest) && (theSquare[2][2] == variableTest))
        return true

    if((theSquare[0][2] == variableTest) && (theSquare[1][1] == variableTest) && (theSquare[2][0] == variableTest))
        return true

    return false
}


fun computersChoice(){
    var enteredChoice = false
    do{
        val x = Random().nextInt(1, 4 )
        val y = Random().nextInt(1, 4 )
        if (theSquare[x-1][y-1] == "") {
            enteredChoice = true
            theSquare[x-1][y-1] = "O"
        }
    }while(!enteredChoice)
}


fun enterUserChoice(){
    var enteredChoice = false
    do {
        println("Please enter a position eg: top right is 1,1")
        val input = readLine() ?: ""
        var x: Int
        var y: Int

        try {
            val positions = input.split(",")
            x = positions[1].trim().toInt()
            y = positions[0].trim().toInt()
            if (theSquare[x-1][y-1] == "") {
                enteredChoice = true
                theSquare[x-1][y-1] = "X"
            }else{
                println("Already occupied space")
            }
        } catch (e: Exception) {
            println("Invalid Input, please attempt again")
            continue
        }
    }while(!enteredChoice)

}
fun createTheSquareBlanks() {
    for (i in 0..2) {
        theSquare.add(arrayListOf("", "", ""))
    }
}

fun printTheBoard(){
    val barInSquare = "-------------"

    println(barInSquare)
    for(i in 0..2){
        for(box in 0..2){
            when(box){
                0,2 -> print("| ${if(theSquare[i][box] =="") " " else theSquare[i][box]} |")
                else -> print(" ${if(theSquare[i][box] =="") " " else theSquare[i][box]} ")
            }
        }
        println("")
        println(barInSquare)
    }
}