import React, { useState } from "react";
import ListOfPlayers from "./ListOfPlayers";
import IndianPlayers from "./IndianPlayers";

const Task9 = () => {
  const [showListOfPlayers, setShowListOfPlayers] = useState(true);

  const toggleComponent = () => {
    setShowListOfPlayers(!showListOfPlayers);
  };

  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h1 className="text-3xl font-bold mb-6">Cricket Application</h1>
      <button
        onClick={toggleComponent}
        className="bg-green-500 text-white p-2 rounded-md mb-4"
      >
        Toggle Component
      </button>
      {showListOfPlayers ? <ListOfPlayers /> : <IndianPlayers />}
    </div>
  );
};

export default Task9;
