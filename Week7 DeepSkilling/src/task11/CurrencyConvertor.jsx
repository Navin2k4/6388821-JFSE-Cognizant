import React, { useState } from "react";

const CurrencyConvertor = () => {
  const [amount, setAmount] = useState("");
  const [currency, setCurrency] = useState("Euro");

  const handleAmountChange = (e) => {
    setAmount(e.target.value);
  };

  const handleCurrencyChange = (e) => {
    setCurrency(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const indianRupees = parseFloat(amount);
    if (isNaN(indianRupees)) {
      alert("Please enter a valid amount.");
      return;
    }

    const euroRate = 0.011; // 1 INR = 0.011 Euro (example rate)
    const euroAmount = indianRupees * euroRate;
    alert(`Converting to Euro Amount is ${euroAmount.toFixed(2)}`);
  };

  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h2 className="text-2xl font-bold mb-4">Currency Convertor!!!</h2>
      <form onSubmit={handleSubmit} className="flex flex-col gap-4">
        <div>
          <label
            htmlFor="amount"
            className="block text-lg font-medium text-gray-700"
          >
            Amount:
          </label>
          <input
            type="number"
            id="amount"
            value={amount}
            onChange={handleAmountChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
          />
        </div>
        <div>
          <label
            htmlFor="currency"
            className="block text-lg font-medium text-gray-700"
          >
            Currency:
          </label>
          <input
            type="text"
            id="currency"
            value={currency}
            onChange={handleCurrencyChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm"
            disabled
          />
        </div>
        <button type="submit" className="bg-blue-500 text-white p-2 rounded-md">
          Submit
        </button>
      </form>
    </div>
  );
};

export default CurrencyConvertor;
