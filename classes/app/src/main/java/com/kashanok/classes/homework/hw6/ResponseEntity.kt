package com.kashanok.classes.homework.hw6

data class Response(val name: String, val gender: Int, val date: String, val people: List<Student>)

data class Student(val id: Int, val name: String, val surname: String, val age: Int, val isDegree: Boolean)