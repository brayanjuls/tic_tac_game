package startup.juls.com.startup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var player1=ArrayList<Int>()
    private var player2=ArrayList<Int>()
    private var activePlayer=1
    private var winner=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun butClick(view:View){

        val buSelected=view as Button
        var cellId=0
        when(buSelected.id){
            R.id.bu1->cellId=1
            R.id.bu2->cellId=2
            R.id.bu3->cellId=3
            R.id.bu4->cellId=4
            R.id.bu5->cellId=5
            R.id.bu6->cellId=6
            R.id.bu7->cellId=7
            R.id.bu8->cellId=8
            R.id.bu9->cellId=9
        }

        playGame(cellId,buSelected)
       // Toast.makeText(this,"Your cell id is $cellId",Toast.LENGTH_LONG).show()
    }

    private fun playGame(cellId:Int,buSelected:Button){
        if(activePlayer==1){
            buSelected.text="X"
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer=2
            checkWinner()
            autoPlay()
        }else{
            buSelected.text="O"
            buSelected.setBackgroundResource(R.color.orange)
            player2.add(cellId)
            activePlayer=1
            checkWinner()
        }

        buSelected.isEnabled=false

    }

    private fun checkWinner(){

        if(Arrays.asList(1,2,3).all { a->player1.contains(a) } || Arrays.asList(3,4,5).all { a->player1.contains(a) }
                || Arrays.asList(6,7,8).all { a->player1.contains(a) } || Arrays.asList(1,4,7).all { a->player1.contains(a) }
                || Arrays.asList(2,5,8).all { a->player1.contains(a) } || Arrays.asList(3,6,9).all { a->player1.contains(a) }
                || Arrays.asList(1,5,9).all { a->player1.contains(a) } || Arrays.asList(3,5,7).all { a->player1.contains(a) }){
            winner=1
        }else  if(Arrays.asList(1,2,3).all { a->player2.contains(a) } || Arrays.asList(3,4,5).all { a->player2.contains(a) }
                || Arrays.asList(6,7,8).all { a->player2.contains(a) } || Arrays.asList(1,4,7).all { a->player2.contains(a) }
                || Arrays.asList(2,5,8).all { a->player2.contains(a) } || Arrays.asList(3,6,9).all { a->player2.contains(a) }
                || Arrays.asList(1,5,9).all { a->player2.contains(a) } || Arrays.asList(3,5,7).all { a->player2.contains(a) }){
            winner=2
        }

        if(winner!=-1){
            if(winner==1){
                Toast.makeText(this,"Player 1 is the Winner congrats",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Player 2 is the Winner congrats",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun autoPlay(){
        if(winner!=-1)
            return

            var emptyCells=ArrayList<Int>()
            for(cellId in 1..9){
                if(!(player1.contains(cellId) || player2.contains(cellId))){
                    emptyCells.add(cellId)
                }
            }

            val randomIndex=Random().nextInt(emptyCells.size)
            var cellId=emptyCells[randomIndex]

            var selectedButton:Button = when(cellId){
                1-> bu1
                2-> bu2
                3-> bu3
                4-> bu4
                5-> bu5
                6-> bu6
                7-> bu7
                8-> bu8
                9-> bu9
                else->{
                    bu1
                }
            }

            playGame(cellId,selectedButton)

    }
}
