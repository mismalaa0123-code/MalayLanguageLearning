package com.example.malaylanguage.utils

object Constants {
    // App Info
    const val APP_NAME = "মালয়েশিয়ান ভাষা শিক্ষা"
    const val APP_TAGLINE = "বাংলা থেকে সহজে মালয় ভাষা শিখুন"
    const val APP_VERSION = "1.0.0"

    // Database
    const val DATABASE_FILE = "malay_language_database.json"
    const val PREFERENCES_NAME = "malay_language_prefs"

    // Preferences Keys
    const val PREF_DARK_MODE = "dark_mode"
    const val PREF_LEARNING_COUNT = "learning_count"
    const val PREF_FAVORITES = "favorites"
    const val PREF_LAST_OPENED = "last_opened"

    // TTS
    const val TTS_LANGUAGE = "ms-MY" // Malaysian Malay
    const val TTS_LANGUAGE_FALLBACK = "ms" // Malay

    // Search
    const val MIN_SEARCH_LENGTH = 1
    const val SEARCH_DEBOUNCE_MS = 300L

    // Categories IDs
    object CategoryIds {
        const val GREETINGS = "greetings"
        const val DAILY_CONVERSATION = "daily_conversation"
        const val FAMILY = "family"
        const val FRIENDS = "friends"
        const val FOOD = "food"
        const val RESTAURANT = "restaurant"
        const val SHOPPING = "shopping"
        const val HOSPITAL = "hospital"
        const val HEALTH = "health"
        const val WORKPLACE = "workplace"
        const val FACTORY = "factory"
        const val CONSTRUCTION = "construction"
        const val HOTEL = "hotel"
        const val OFFICE = "office"
        const val BOSS = "boss"
        const val COLLEAGUES = "colleagues"
        const val TRANSPORT = "transport"
        const val BUS = "bus"
        const val TRAIN = "train"
        const val TAXI = "taxi"
        const val DIRECTION = "direction"
        const val HOME = "home"
        const val RENTAL = "rental"
        const val IMMIGRATION = "immigration"
        const val PASSPORT = "passport"
        const val WORK_PERMIT = "work_permit"
        const val GOVERNMENT = "government"
        const val POLICE = "police"
        const val EMERGENCY = "emergency"
        const val BANK = "bank"
        const val MONEY = "money"
        const val MOBILE = "mobile"
        const val INTERNET = "internet"
        const val SIM_CARD = "sim_card"
        const val TIME = "time"
        const val NUMBERS = "numbers"
        const val WEATHER = "weather"
        const val GENERAL_QA = "general_qa"
        const val YES_NO = "yes_no"
        const val VOCABULARY = "vocabulary"
        const val DAILY_PHRASES = "daily_phrases"
        const val POLITE_PHRASES = "polite_phrases"
        const val HELP_PHRASES = "help_phrases"
        const val PROBLEM_PHRASES = "problem_phrases"
        const val AIRPORT = "airport"
    }

    // Difficulty Levels
    object Difficulty {
        const val BEGINNER = "beginner"
        const val INTERMEDIATE = "intermediate"
        const val ADVANCED = "advanced"
    }

    // Types
    object ItemType {
        const val WORD = "word"
        const val PHRASE = "phrase"
        const val SENTENCE = "sentence"
        const val QUESTION = "question"
        const val ANSWER = "answer"
        const val EMERGENCY = "emergency"
    }
}
