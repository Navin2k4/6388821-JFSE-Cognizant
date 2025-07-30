
const CalculateScore = ({ name, school, total, goal }) => {
  const average = total / goal;

  return (
    <div style={{ border: '1px solid gray', padding: '20px', margin: '20px', borderRadius: '10px' }}>
      <h2>Student Score Details</h2>
      <p>{school}</p>
      <p>{total}</p>
      <p>{name}</p>
      <p>{goal}</p>
      <p>{average.toFixed(2)}</p>
    </div>
  );
};

export default CalculateScore;
