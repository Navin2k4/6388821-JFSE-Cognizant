import React from "react";

const GuestPage = ({ onLogin }) => {
  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h2 className="text-2xl font-bold mb-4">Please sign up.</h2>
      <button
        onClick={onLogin}
        className="bg-blue-500 text-white p-2 rounded-md"
      >
        Login
      </button>
    </div>
  );
};

export default GuestPage;
