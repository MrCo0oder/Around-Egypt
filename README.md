# Create a README.md file with the provided content

# 🌍 **Around Egypt - Virtual Tours App**  
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-%230080FF.svg?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/compose)  
[![Kotlin](https://img.shields.io/badge/Kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)  
[![MVVM Architecture](https://img.shields.io/badge/Architecture-MVVM-blue?style=for-the-badge)](https://developer.android.com/jetpack/guide)  
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](LICENSE)  

A **Jetpack Compose** app that lists **virtual tour experiences** in Egypt. The app follows **MVVM architecture**, supports **offline caching**, and allows users to **like experiences**.

---

## 📌 **Features**
✅ **Home Screen**  
- 🏛 **Recommended Experiences** (Horizontal List).  
- 🏺 **Most Recent Experiences** (Vertical List).  
- 🎯 Click an experience to **view details**.  

✅ **Search Functionality**  
- 🔍 Users can **search** for experiences.  
- 🚀 Uses **IME search action** to trigger search.  
- 📝 Displays **real-time search results**.  

✅ **Like an Experience**  
- ❤️ Users can **like** an experience (*no unlike option*).  
- 🔄 Likes update via **API** & are stored **locally**.  

✅ **Offline Caching**  
- 📶 Uses **Room Database** to store experiences **offline**.  

---

## 🏗 **Tech Stack**
### 🎨 **Frontend**
- 🖥 **Jetpack Compose (UI Framework)**  
- 🚀 **Navigation Compose** (For screen navigation)  

### 🏛 **Architecture**
- **MVVM (Model-View-ViewModel)**
- **StateFlow** for UI state management  

### 📦 **Libraries Used**
| Purpose               | Library |
|----------------------|---------|
| 🖼 UI Framework         | Jetpack Compose |
| 🧭 Navigation           | Navigation Compose |
| 🏗 Dependency Injection | Hilt |
| 🌐 Networking          | Retrofit |
| 🖼 Image Loading       | Coil |
| 🗄 Local Storage       | Room Database |
| 🔄 State Management    | ViewModel + StateFlow |

---

## 🚀 **Installation & Setup**
### 📥 1️⃣ Clone the Repository  
```sh
git clone https://github.com/MrCo0oder/around-egypt.git
cd around-egypt
