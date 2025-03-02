# 🍽️ Restaurant Order Scheduler - Learn Scheduling Algorithms  

An interactive Android app built with **Jetpack Compose** that teaches **order scheduling algorithms** in a restaurant setting. Users can select meals, set priorities, and visualize how different scheduling methods process orders efficiently.  

## 📌 Features  

✅ **Intuitive UI:** Select meals with animated UI cards  
✅ **Multiple Scheduling Algorithms:**  
   - **FCFS (First Come, First Served)**  
   - **SJF (Shortest Job First)**  
   - **Priority Scheduling**  
   - **Round Robin (Time Quantum-Based)**  
   - **Multilevel Queue Scheduling**  
✅ **Visual Execution Log:** Watch how orders are processed dynamically  
✅ **Algorithm Comparison:** See performance differences in scheduling  

## 🎯 What You'll Learn  

🔹 How **CPU scheduling algorithms** apply to real-world problems  
🔹 The impact of **order priority & processing time** on efficiency  
🔹 Implementing **MVVM architecture** in Jetpack Compose  
🔹 Using **State Management** for UI updates  

## 🖥️ Screenshots  

## 🏗️ Architecture

This project follows the MVVM (Model-View-ViewModel) pattern for clean code and separation of concerns.

📂 app  
 ┣ 📂 ui           # Jetpack Compose UI components  
 ┣ 📂 viewmodel    # Handles state and logic  
 ┣ 📂 model        # Data models (Meal, Order, etc.)  
 ┗ 📂 repository   # Manages data flow  
  

 
## 🚀 Getting Started  

### 🔧 Prerequisites  
- **Android Studio** (Latest version)  
- **Jetpack Compose & Kotlin**  

### 📥 Installation  
1️⃣ **Clone the repository**  

https://github.com/ShimaaAboelmagd257/ResturantOrderSchedular.git

2️⃣ **Open in Android Studio**

3️⃣ **Run the project on an emulator or physical device**

## 🧠 Understanding the Algorithms
1️⃣ **First Come, First Served (FCFS)**
    Orders are served in the exact order they are placed.
    Real-world analogy: A queue at a fast-food counter.

2️⃣ **Shortest Job First (SJF)**
    Orders with the shortest prep time are prioritized.
    Real-world analogy: Preparing quick meals first to optimize service.

3️⃣ **Priority Scheduling**
    Orders with higher priority (e.g., VIP customers) are processed first.
    Real-world analogy: Handling urgent orders first in a busy restaurant.

4️⃣ **Round Robin Scheduling**
    Orders are processed in fixed time slices (time quantum).
    Real-world analogy: A chef switching between multiple tasks evenly.

5️⃣ **Multilevel Queue Scheduling**
    Orders are grouped into different priority queues.
    Real-world analogy: Separating dine-in, takeout, and delivery orders.

## 🛠️ Tech Stack
  -  Kotlin
  -  Jetpack Compose
  -  Navigation Component
  -  LiveData / StateFlow
  -  Coroutines

## 🎉 Contributing

- Pull requests are welcome! If you'd like to add more scheduling techniques or improve UI animations, feel free to contribute.

## 📬 Contact

For suggestions, feel free to reach out!
📧 Email: shimaaaboelmagd257@gmai.com
