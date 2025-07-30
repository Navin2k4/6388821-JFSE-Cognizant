import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="App">
      <h1>Student Management Portal</h1>
      <CalculateScore
        name="John Doe"
        school="Greenwood High"
        total={450}
        goal={5}
      />
    </div>
  );
}

export default App;
