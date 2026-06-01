/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        background: 'var(--background)',
        foreground: 'var(--foreground)',
        'primary': '#0f172a',
        'primary-light': '#1e293b',
        'secondary': '#ffffff',
        'accent': '#3b82f6',
        'accent-light': '#60a5fa',
        'success': '#10b981',
        'warning': '#f59e0b',
        'danger': '#ef4444',
        'info': '#0ea5e9',
      },
      backgroundColor: {
        'gradient': 'linear-gradient(135deg, #ffffff 0%, #0f172a 100%)',
      }
    },
  },
  plugins: [],
  darkMode: 'class',
}
