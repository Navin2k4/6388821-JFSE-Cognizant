import React from "react";

const BookDetails = ({ books }) => {
  return (
    <div className="border-l-4 border-green-500 pl-4 py-2">
      <h2 className="text-2xl font-bold mb-4">Book Details</h2>
      {books.length > 0 &&
        books.map((book) => (
          <div key={book.id} className="mb-4">
            <h3 className="text-xl font-semibold">{book.title}</h3>
            <p>{book.pages}</p>
          </div>
        ))}
      {books.length === 0 && <p>No book details available.</p>}
    </div>
  );
};

export default BookDetails;
