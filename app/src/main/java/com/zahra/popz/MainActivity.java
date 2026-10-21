package com.example.journeyapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        setContentView(webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String htmlContent = """
            <!DOCTYPE html>
            <html lang="ar" dir="rtl">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>رحلتي وإنجازاتي 🎀</title>
                <script src="https://cdn.tailwindcss.com"></script>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=Tajawal:wght@400;500;700;800&display=swap" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/canvas-confetti@1.6.0/dist/confetti.browser.min.js"></script>
                <script>
                    tailwind.config = {
                        theme: {
                            extend: {
                                colors: {
                                    pastelPink: '#FFD1DC',
                                    deepPink: '#FF85A1',
                                    lavender: '#E6E6FA',
                                    deepLavender: '#9B82CF',
                                    softWhite: '#FFF9FB',
                                    accentGold: '#FFD700',
                                },
                                fontFamily: {
                                    tajawal: ['Tajawal', 'sans-serif'],
                                }
                            }
                        }
                    }
                </script>
                <style>
                    body {
                        font-family: 'Tajawal', sans-serif;
                        background: linear-gradient(135deg, #FFF0F5 0%, #F3E5F5 100%);
                        min-height: 100vh;
                        user-select: none;
                    }
                    @keyframes float {
                        0% { transform: translateY(0px) rotate(0deg); }
                        50% { transform: translateY(-8px) rotate(3deg); }
                        100% { transform: translateY(0px) rotate(0deg); }
                    }
                    .animate-float { animation: float 4s ease-in-out infinite; }
                    ::-webkit-scrollbar { width: 6px; }
                    ::-webkit-scrollbar-track { background: #FFF0F5; }
                    ::-webkit-scrollbar-thumb { background: #FFB6C1; border-radius: 10px; }
                    .glass-card { background: rgba(255, 255, 255, 0.85); backdrop-filter: blur(10px); border: 1px solid rgba(255, 182, 193, 0.3); }
                    .completed-card { background: linear-gradient(135deg, #FFB6C1 0%, #D8BFD8 100%); border-color: #FF85A1; }
                    .uncompleted-card { background: #F0F0F0; border-color: #E0E0E0; color: #757575; }
                </style>
            </head>
            <body class="text-gray-800 pb-20 md:pb-10">
                <header class="sticky top-0 z-30 glass-card shadow-sm px-4 py-3 mb-6">
                    <div class="max-w-4xl mx-auto flex justify-between items-center">
                        <div class="flex items-center space-x-2 space-x-reverse">
                            <span class="text-2xl animate-float">🎀</span>
                            <h1 class="text-xl md:text-2xl font-bold text-pink-600 tracking-wide">رحلتي وإنجازاتي</h1>
                        </div>
                        <nav class="flex space-x-2 space-x-reverse text-xs md:text-sm font-medium">
                            <button onclick="switchTab('home')" id="tab-home" class="tab-btn px-3 py-1.5 rounded-full bg-pink-500 text-white shadow-md transition-all flex items-center gap-1">
                                <i class="fa-solid fa-house"></i>
                                <span>الرئيسية</span>
                            </button>
                            <button onclick="switchTab('rewards')" id="tab-rewards" class="tab-btn px-3 py-1.5 rounded-full bg-white text-pink-600 hover:bg-pink-100 transition-all flex items-center gap-1">
                                <i class="fa-solid fa-gift"></i>
                                <span>مكافآتي 🌸</span>
                            </button>
                            <button onclick="switchTab('settings')" id="tab-settings" class="tab-btn px-3 py-1.5 rounded-full bg-white text-pink-600 hover:bg-pink-100 transition-all flex items-center gap-1">
                                <i class="fa-solid fa-gear"></i>
                                <span>الإعدادات</span>
                            </button>
                        </nav>
                    </div>
                </header>
                <main class="max-w-4xl mx-auto px-4">
                    <div id="view-home" class="tab-content space-y-6">
                        <div class="glass-card rounded-3xl p-5 shadow-sm relative overflow-hidden">
                            <div class="flex justify-between items-center mb-1">
                                <h2 class="text-lg font-bold text-pink-600">هدف العناية بنفسي 💖</h2>
                                <button onclick="openGoalModal()" class="text-xs text-purple-500 hover:underline"><i class="fa-solid fa-pen"></i> تعديل</button>
                            </div>
                            <p id="display-goal" class="text-gray-700 bg-pink-50/60 p-3 rounded-2xl border border-pink-100 text-sm">تطوير عاداتي الصحية، الاستمتاع بوقتي، والعناية بجسدي وروحي بكل حب ولطف 🌷</p>
                        </div>
                        <div class="glass-card rounded-3xl p-5 shadow-sm text-center relative">
                            <div class="flex justify-between items-center mb-2">
                                <span class="text-xs font-bold text-pink-600">نسبة الإنجاز والتألق</span>
                                <span id="progress-text" class="text-xs font-bold text-purple-600 bg-purple-100 px-2.5 py-1 rounded-full">0 / 30 مكتمل</span>
                            </div>
                            <div class="w-full bg-gray-200 h-5 rounded-full overflow-hidden p-0.5 border border-pink-200 shadow-inner">
                                <div id="progress-bar" class="bg-gradient-to-r from-pink-400 to-purple-500 h-full rounded-full transition-all duration-700" style="width: 0%"></div>
                            </div>
                        </div>
                        <div class="flex justify-between items-center px-1">
                            <h3 class="text-base font-bold text-gray-700">جدول الإنجازات والمكافآت (30)</h3>
                        </div>
                        <div id="squares-grid" class="grid grid-cols-3 sm:grid-cols-4 md:grid-cols-5 lg:grid-cols-6 gap-3.5"></div>
                    </div>
                    <div id="view-rewards" class="tab-content hidden space-y-6">
                        <div class="glass-card rounded-3xl p-6 shadow-sm text-center">
                            <h2 class="text-xl font-bold text-pink-600 mb-2">خزانة مكافآتي الجميلة</h2>
                            <div id="unlocked-rewards-list" class="space-y-3 text-right"></div>
                        </div>
                    </div>
                    <div id="view-settings" class="tab-content hidden space-y-6">
                        <div class="glass-card rounded-3xl p-6 shadow-sm space-y-4">
                            <h2 class="text-lg font-bold text-pink-600">الإعدادات</h2>
                            <button onclick="resetAllData()" class="w-full py-3 px-4 bg-red-50 text-red-500 font-bold rounded-2xl text-xs">إعادة تعيين كافة البيانات</button>
                        </div>
                    </div>
                </main>
                <div id="square-modal" class="fixed inset-0 bg-black/40 backdrop-blur-sm z-50 flex items-center justify-center p-4 hidden">
                    <div class="bg-white rounded-3xl max-w-sm w-full p-6 shadow-2xl space-y-4 relative">
                        <button onclick="closeModal('square-modal')" class="absolute top-4 left-4 text-gray-400 font-bold">✕</button>
                        <div class="text-center"><h3 id="modal-square-title" class="text-xl font-extrabold text-pink-600">مربع</h3></div>
                        <div class="bg-pink-50/80 rounded-2xl p-3.5 border border-pink-100">
                            <span class="text-xs font-bold text-gray-500">المكافأة:</span>
                            <p id="modal-reward-text" class="text-sm font-bold text-pink-700"></p>
                        </div>
                        <div class="grid grid-cols-2 gap-2 pt-2">
                            <button onclick="markSquareStatus(true)" class="bg-pink-500 text-white font-bold text-xs py-2.5 rounded-2xl">أنجزته! 🌸</button>
                            <button onclick="markSquareStatus(false)" class="bg-gray-100 text-gray-600 font-bold text-xs py-2.5 rounded-2xl">ليس بعد</button>
                        </div>
                    </div>
                </div>
                <div id="goal-modal" class="fixed inset-0 bg-black/40 backdrop-blur-sm z-50 flex items-center justify-center p-4 hidden">
                    <div class="bg-white rounded-3xl max-w-sm w-full p-5 space-y-3">
                        <h3 class="text-base font-bold text-pink-600">تعديل الهدف الشخصي</h3>
                        <textarea id="goal-input-text" rows="3" class="w-full text-xs p-3 rounded-2xl border border-pink-200"></textarea>
                        <div class="flex justify-end gap-2">
                            <button onclick="closeModal('goal-modal')" class="px-3 py-1.5 text-xs text-gray-500 bg-gray-100 rounded-xl">إلغاء</button>
                            <button onclick="savePersonalGoal()" class="px-3 py-1.5 text-xs text-white bg-pink-500 rounded-xl font-bold">حفظ</button>
                        </div>
                    </div>
                </div>
                <script>
                    let currentSquareId = null;
                    let squaresData = JSON.parse(localStorage.getItem('squares_data')) || Array.from({length: 30}, (_, i) => ({
                        id: i + 1, completed: false, reward: `مكافأة لطيفة رقم ${i + 1} 🎁`, note: ''
                    }));
                    function switchTab(tabId) {
                        document.querySelectorAll('.tab-content').forEach(el => el.classList.add('hidden'));
                        document.querySelectorAll('.tab-btn').forEach(el => {
                            el.classList.remove('bg-pink-500', 'text-white', 'shadow-md');
                            el.classList.add('bg-white', 'text-pink-600');
                        });
                        document.getElementById('view-' + tabId).classList.remove('hidden');
                        const activeBtn = document.getElementById('tab-' + tabId);
                        activeBtn.classList.remove('bg-white', 'text-pink-600');
                        activeBtn.classList.add('bg-pink-500', 'text-white', 'shadow-md');
                        if(tabId === 'rewards') renderRewards();
                    }
                    function renderGrid() {
                        const grid = document.getElementById('squares-grid');
                        grid.innerHTML = '';
                        let completedCount = 0;
                        squaresData.forEach((sq, idx) => {
                            if(sq.completed) completedCount++;
                            const div = document.createElement('div');
                            div.className = `aspect-square rounded-2xl p-2 flex flex-col justify-between items-center cursor-pointer border shadow-sm transition-all transform hover:scale-105 ${sq.completed ? 'completed-card text-white' : 'uncompleted-card'}`;
                            div.onclick = () => openSquareModal(sq.id);
                            div.innerHTML = `
                                <span class="text-[10px] font-bold opacity-70">#${sq.id}</span>
                                <span class="text-xl">${sq.completed ? '✨' : '☁️'}</span>
                                <span class="text-[9px] font-bold truncate w-full text-center">${sq.completed ? 'مكتمل' : 'قيد الانتظار'}</span>
                            `;
                            grid.appendChild(div);
                        });
                        document.getElementById('progress-text').innerText = `${completedCount} / 30 مكتمل`;
                        document.getElementById('progress-bar').style.width = `${(completedCount / 30) * 100}%`;
                        localStorage.setItem('squares_data', JSON.stringify(squaresData));
                    }
                    function openSquareModal(id) {
                        currentSquareId = id;
                        const sq = squaresData.find(s => s.id === id);
                        document.getElementById('modal-square-title').innerText = `مربع رقم #${sq.id}`;
                        document.getElementById('modal-reward-text').innerText = sq.reward;
                        document.getElementById('square-modal').classList.remove('hidden');
                    }
                    function markSquareStatus(status) {
                        const sq = squaresData.find(s => s.id === currentSquareId);
                        sq.completed = status;
                        closeModal('square-modal');
                        renderGrid();
                        if(status && typeof confetti === 'function') {
                            confetti({ particleCount: 80, spread: 60, origin: { y: 0.6 } });
                        }
                    }
                    function openGoalModal() {
                        document.getElementById('goal-input-text').value = document.getElementById('display-goal').innerText;
                        document.getElementById('goal-modal').classList.remove('hidden');
                    }
                    function savePersonalGoal() {
                        const text = document.getElementById('goal-input-text').value;
                        if(text.trim()) {
                            document.getElementById('display-goal').innerText = text;
                            localStorage.setItem('personal_goal', text);
                        }
                        closeModal('goal-modal');
                    }
                    function closeModal(modalId) {
                        document.getElementById(modalId).classList.add('hidden');
                    }
                    function renderRewards() {
                        const list = document.getElementById('unlocked-rewards-list');
                        list.innerHTML = '';
                        const unlocked = squaresData.filter(s => s.completed);
                        if(unlocked.length === 0) {
                            list.innerHTML = `<p class="text-xs text-gray-400 text-center py-4">لم تفتحي أي مكافأة بعد.. أبدي بإنجاز المربعات! 🌸</p>`;
                            return;
                        }
                        unlocked.forEach(s => {
                            const item = document.createElement('div');
                            item.className = 'bg-pink-50 p-3 rounded-2xl border border-pink-100 flex justify-between items-center text-xs';
                            item.innerHTML = `<span>🎁 مربّع #${s.id}: <strong>${s.reward}</strong></span> <span class="text-pink-500">تم الفتح ✨</span>`;
                            list.appendChild(item);
                        });
                    }
                    function resetAllData() {
                        if(confirm('متأكدة تبين تعيدين تعيين كل شي؟')) {
                            localStorage.clear();
                            location.reload();
                        }
                    }
                    window.onload = () => {
                        const savedGoal = localStorage.getItem('personal_goal');
                        if(savedGoal) document.getElementById('display-goal').innerText = savedGoal;
                        renderGrid();
                    };
                </script>
            </body>
            </html>
            """;

        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
    }
}
