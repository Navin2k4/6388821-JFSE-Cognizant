import React, { useState } from "react";

const EventExamples = () => {
  const [counter, setCounter] = useState(0);

  const increment = () => {
    setCounter(counter + 1);
  };

  const sayHello = () => {
    alert("Hello! Member!");
  };

  const incrementAndSayHello = () => {
    increment();
    sayHello();
  };

  const decrement = () => {
    setCounter(counter - 1);
  };

  const sayWelcome = (message) => {
    alert(message);
  };

  const handleClick = (event) => {
    alert("I was clicked");
    console.log("Synthetic Event:", event);
  };

  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h2 className="text-2xl font-bold mb-4">Event Handling Examples</h2>

      <div className="mb-4">
        <p className="text-xl mb-2">Counter: {counter}</p>
        <button
          onClick={incrementAndSayHello}
          className="bg-blue-500 text-white p-2 rounded-md mr-2"
        >
          Increment
        </button>
        <button
          onClick={decrement}
          className="bg-red-500 text-white p-2 rounded-md"
        >
          Decrement
        </button>
      </div>

      <div className="mb-4">
        <button
          onClick={() => sayWelcome("welcome")}
          className="bg-green-500 text-white p-2 rounded-md"
        >
          Say Welcome
        </button>
      </div>

      <div>
        <button
          onClick={handleClick}
          className="bg-purple-500 text-white p-2 rounded-md"
        >
          Click on me
        </button>
      </div>
    </div>
  );
};

export default EventExamples;
