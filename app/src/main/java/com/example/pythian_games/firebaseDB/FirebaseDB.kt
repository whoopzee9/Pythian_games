package com.example.pythian_games.firebaseDB

import com.example.pythian_games.data.CleaningQuestion
import com.example.pythian_games.data.Question
import com.example.pythian_games.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseDB: ExtensionsCRUD {

    private var gamesRef = FirebaseDatabase.getInstance().getReference("GAMES")
    private var usersRef = FirebaseDatabase.getInstance().getReference("USERS")
    private var questionsRef = FirebaseDatabase.getInstance().getReference("QUESTIONS")

    private var auth = FirebaseAuth.getInstance()
    private var user = auth.currentUser

    override fun createRoom(name: String, secretCode: String) {
        TODO("Not yet implemented")
    }

    override fun joinRoom(secretCode: String) {
        TODO("Not yet implemented")
    }

    override fun deleteRoom(name: String) {
        TODO("Not yet implemented")
    }

    override fun addQuestion(question: Question) {
        questionsRef.child(question.questionId.toString()).setValue(question)
    }

    override fun getAllQuestions(callBack: (MutableList<Question>) -> Unit) {
        var questions: MutableList<Question> = mutableListOf()
        questionsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    //println(snapshot.getValue(User::class.java))
                    for (item in snapshot.children) {
                        //println(item.value)
                        val retrieveQuestion = item.getValue(Question::class.java)
                        if (retrieveQuestion != null) {
                            questions.add(retrieveQuestion)
                        }
                    }
                }
                callBack(questions)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun createUser(userData: User) {
        usersRef.child(userData.id).setValue(userData)
    }

    override fun addQuestionNumberToUsersList(number: Int) {
        TODO("Not yet implemented")
    }

    override fun addCleaningQuestion(question: CleaningQuestion) {
        TODO("Not yet implemented")
    }

    override fun getCleaningQuestions(callBack: (MutableList<CleaningQuestion>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun deleteAnsweredQuestion(cardNumber: Int) {
        TODO("Not yet implemented")
    }

}