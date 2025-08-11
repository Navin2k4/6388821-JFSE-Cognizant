import React from "react";
import EventExamples from "./EventExamples";
import CurrencyConvertor from "./CurrencyConvertor";

const Task11 = () => {
  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h1 className="text-3xl font-bold mb-6">
        Event Handling and Currency Conversion
      </h1>
      <EventExamples />
      <hr className="my-8 w-full border-gray-300" />
      <CurrencyConvertor />
    </div>
  );
};

export default Task11;
