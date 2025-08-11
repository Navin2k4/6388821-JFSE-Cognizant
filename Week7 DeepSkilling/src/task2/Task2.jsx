import React from 'react'
import About from './About'
import Contact from './Contact'
import Home from './Home'

const Task2 = () => {
  return (
    <div className='flex flex-col items-center justify-center min-h-screen bg-gray-100 p-4'>
        <h1 className='text-3xl font-bold mb-6 text-gray-800'>Components Usage</h1>
        <div className='bg-white p-8 rounded-lg shadow-md w-full max-w-md space-y-4'>
            <Home />
            <About />
            <Contact />
        </div>
    </div>
  )
}

export default Task2