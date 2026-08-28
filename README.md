# মালয়েশিয়ান ভাষা শিক্ষা
## Malaysian Language Learning for Bengali Speakers

### বিবরণ | Description
এটি একটি Production-Ready Android Application যা বাংলা ভাষাভাষী মানুষদের জন্য সহজে মালয় ভাষা শিখতে সাহায্য করে।

This is a professional Android application designed to help Bengali speakers learn Malaysian (Malay) language easily.

### বৈশিষ্ট্য | Features
- 🗣️ দৈনিক কথোপকথন (Daily Conversations)
- 🔍 তাৎক্ষণিক অনুসন্ধান (Instant Search)
- ❤️ প্রিয় সংরক্ষণ (Favorite System)
- 🌙 ডার্ক মোড সমর্থন (Dark Mode Support)
- 🔊 অডিও উচ্চারণ (Text-to-Speech)
- 📱 অফলাইন ডাটাবেস (Offline Database)
- 📊 শেখার অগ্রগতি (Learning Progress)
- 🎨 আধুনিক ইউআই (Modern Professional UI)

### প্রযুক্তি স্ট্যাক | Tech Stack
- **Language**: Kotlin
- **Build System**: Gradle (Kotlin DSL)
- **Min SDK**: 24
- **Target SDK**: 34
- **Database**: JSON (Offline)
- **Architecture**: MVVM Pattern
- **UI Framework**: Android Jetpack
  - Fragment
  - LiveData
  - ViewModel
  - Navigation Component
- **Storage**: DataStore
- **JSON Parsing**: GSON
- **TTS**: Android TextToSpeech API

### প্রজেক্ট কাঠামো | Project Structure
```
MalayLanguageLearning/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/malaylanguage/
│   │   │   │   ├── ui/
│   │   │   │   ├── adapter/
│   │   │   │   ├── data/
│   │   │   │   ├── database/
│   │   │   │   └── utils/
│   │   │   ├── res/
│   │   │   ├── assets/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── .github/workflows/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

### ইনস্টলেশন | Installation

1. Clone the repository
```bash
git clone https://github.com/mismalaa0123-code/MalayLanguageLearning.git
```

2. Open in Android Studio

3. Build and run
```bash
./gradlew build
./gradlew installDebug
```

### ডাটাবেস | Database
অ্যাপটি 5,000+ মালয় শব্দ এবং বাক্য সমৃদ্ধ একটি স্থানীয় JSON ডাটাবেস ব্যবহার করে।

Database Location: `app/src/main/assets/malay_language_database.json`

### বিল্ড | Building

#### Debug Build
```bash
./gradlew assembleDebug
```

#### Release Build
```bash
./gradlew assembleRelease
```

### GitHub Actions
প্রতিটি push-এর সময় স্বয়ংক্রিয়ভাবে APK তৈরি হয়।

Workflow file: `.github/workflows/build.yml`

### অবদান | Contributing
যদি আপনি অবদান রাখতে চান, দয়া করে একটি Pull Request খুলুন।

### লাইসেন্স | License
MIT License - দেখুন LICENSE ফাইল বিস্তারিতের জন্য।

### যোগাযোগ | Contact
- Email: mismalaa0123@gmail.com
- GitHub: @mismalaa0123-code

### সংস্করণ ইতিহাস | Version History
- **v1.0.0** - প্রাথমিক রিলিজ (Initial Release)

---

**Made with ❤️ for Bengali speakers learning Malaysian**
