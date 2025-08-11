import React from "react";

const CourseDetails = ({ courses }) => {
  return (
    <div className="border-l-4 border-green-500 pl-4 py-2">
      <h2 className="text-2xl font-bold mb-4">Course Details</h2>
      {courses.length > 0 ? (
        courses.map((course) => (
          <div key={course.id} className="mb-4">
            <h3 className="text-xl font-semibold">{course.name}</h3>
            <p>{course.date}</p>
          </div>
        ))
      ) : (
        <p>No course details available.</p>
      )}
    </div>
  );
};

export default CourseDetails;
