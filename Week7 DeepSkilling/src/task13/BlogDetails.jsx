import React from "react";

const BlogDetails = ({ blogs }) => {
  let content;

  if (blogs.length > 0) {
    content = blogs.map((blog) => (
      <div key={blog.id} className="mb-4">
        <h3 className="text-xl font-semibold">{blog.title}</h3>
        <p className="font-medium">{blog.author}</p>
        <p>{blog.snippet}</p>
      </div>
    ));
  } else {
    content = <p>No blog details available.</p>;
  }

  return (
    <div className="border-l-4 border-green-500 pl-4 py-2">
      <h2 className="text-2xl font-bold mb-4">Blog Details</h2>
      {content}
    </div>
  );
};

export default BlogDetails;
