import React from 'react'

const CalculateScore = ({name, school, total, goal}) => {
    const score = total / goal;
  return (
    <div className='flex flex-col items-center justify-center h-screen'>
        <h1><strong>Name: </strong>{name}</h1>
        <h1><strong>School: </strong>{school}</h1>
        <h1><strong>Total: </strong>{total}</h1>
        <h1><strong>Percentage: </strong>{score.toFixed(2)}%</h1>
    </div>
  )
}

export default CalculateScore