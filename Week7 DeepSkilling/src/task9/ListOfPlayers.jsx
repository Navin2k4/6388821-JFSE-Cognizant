import React from "react";

const ListOfPlayers = () => {
  const players = [
    { name: "Player 1", score: 85 },
    { name: "Player 2", score: 60 },
    { name: "Player 3", score: 92 },
    { name: "Player 4", score: 75 },
    { name: "Player 5", score: 55 },
    { name: "Player 6", score: 80 },
    { name: "Player 7", score: 68 },
    { name: "Player 8", score: 95 },
    { name: "Player 9", score: 70 },
    { name: "Player 10", score: 45 },
    { name: "Player 11", score: 88 },
  ];

  const playersBelow70 = players.filter((player) => player.score < 70);

  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h2 className="text-2xl font-bold mb-4">List of Players</h2>
      <h3 className="text-xl font-semibold mb-2">All Players:</h3>
      <ul className="list-disc pl-5">
        {players.map((player, index) => (
          <li key={index}>
            {player.name}: {player.score}
          </li>
        ))}
      </ul>

      <h3 className="text-xl font-semibold mt-4 mb-2">
        Players with Score Below 70:
      </h3>
      <ul className="list-disc pl-5">
        {playersBelow70.map((player, index) => (
          <li key={index}>
            {player.name}: {player.score}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListOfPlayers;
