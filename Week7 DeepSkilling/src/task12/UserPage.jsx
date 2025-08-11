import React from "react";

const UserPage = ({ onLogout }) => {
  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h2 className="text-2xl font-bold mb-4">Welcome back</h2>
      <button
        onClick={onLogout}
        className="bg-red-500 text-white p-2 rounded-md"
      >
        Logout
      </button>
      <p className="mt-4 text-lg">You can now book tickets!</p>
    </div>
  );
};

export default UserPage;
