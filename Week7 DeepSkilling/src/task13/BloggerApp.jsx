import React, { useState } from "react";
import CourseDetails from "./CourseDetails";
import BookDetails from "./BookDetails";
import BlogDetails from "./BlogDetails";

const BloggerApp = () => {
  const [showDetails, setShowDetails] = useState(true);

  const coursesData = [
    { id: 1, name: "Angular", date: "4/5/2021" },
    { id: 2, name: "React", date: "6/3/2020" },
  ];

  const booksData = [
    { id: 1, title: "Master React", pages: 670 },
    { id: 2, title: "Deep Dive Into Angular 11", pages: 800 },
    { id: 3, title: "Mongo Essentials", pages: 450 },
  ];

  const blogsData = [
    {
      id: 1,
      title: "React Learning",
      author: "Stephen Biz",
      snippet: "Welcome to learning React!",
    },
    {
      id: 2,
      title: "Installation",
      author: "Schewzdenier",
      snippet: "You can install React from npm.",
    },
  ];

  const toggleDetails = () => {
    setShowDetails(!showDetails);
  };

  return (
    <div className="flex flex-col items-center justify-center p-4">
      <h1 className="text-3xl font-bold mb-6">Blogger Application</h1>
      <button
        onClick={toggleDetails}
        className="bg-blue-500 text-white p-2 rounded-md mb-4"
      >
        Toggle Details
      </button>

      {showDetails ? (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8 w-full max-w-4xl">
          <CourseDetails courses={coursesData} />
          <BookDetails books={booksData} />
          <BlogDetails blogs={blogsData} />
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8 w-full max-w-4xl">
          <CourseDetails courses={[]} />
          <BookDetails books={[]} />
          <BlogDetails blogs={[]} />
        </div>
      )}
    </div>
  );
};

export default BloggerApp;
