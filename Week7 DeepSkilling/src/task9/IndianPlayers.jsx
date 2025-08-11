import React from "react";

const IndianPlayers = () => {
  const teamPlayers = [
    "Rohit",
    "Rahul",
    "Virat",
    "Pant",
    "Hardik",
    "Jadeja",
    "Bumrah",
    "Shami",
    "Chahal",
    "Siraj",
    "Ashwin",
  ];

  const oddTeamPlayers = teamPlayers.filter((_, index) => index % 2 !== 0);
  const evenTeamPlayers = teamPlayers.filter((_, index) => index % 2 === 0);

  const t20Players = ["Surya", "Ishan", "Deepak"];
  const ranjiTrophyPlayers = ["Pujara", "Rahane", "Vihari"];

  const mergedPlayers = [...t20Players, ...ranjiTrophyPlayers];

  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h2 className="text-2xl font-bold mb-4">Indian Players</h2>

      <h3 className="text-xl font-semibold mb-2">Odd Team Players:</h3>
      <ul className="list-disc pl-5">
        {oddTeamPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>

      <h3 className="text-xl font-semibold mt-4 mb-2">Even Team Players:</h3>
      <ul className="list-disc pl-5">
        {evenTeamPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>

      <h3 className="text-xl font-semibold mt-4 mb-2">
        Merged Players (T20 and Ranji Trophy):
      </h3>
      <ul className="list-disc pl-5">
        {mergedPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
};

export default IndianPlayers;
