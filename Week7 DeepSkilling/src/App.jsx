import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./Home";
import Task1 from "./task1/Task1";
import Task2 from "./task2/Task2";
import Task3 from "./task3/Task3";
import Task4 from "./task4/Task4";
import Task5 from "./task5/CohortDetails";
import Task9 from "./task9/Task9";
import About from "./task2/About";
import Contact from "./task2/Contact";
import OfficeSpaceRental from "./task10/OfficeSpaceRental";
import Task11 from "./task11/Task11";
import TicketBookingApp from "./task12/TicketBookingApp";
import BloggerApp from "./task13/BloggerApp";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/task1" element={<Task1 />} />
        <Route path="/task2" element={<Task2 />} />
        <Route path="/task3" element={<Task3 />} />
        <Route path="/task4" element={<Task4 />} />
        <Route path="/task5" element={<Task5 />} />
        <Route path="/task9" element={<Task9 />} />
        <Route path="/task10" element={<OfficeSpaceRental />} />
        <Route path="/task11" element={<Task11 />} />
        <Route path="/task12" element={<TicketBookingApp />} />
        <Route path="/task13" element={<BloggerApp />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </Router>
  );
};

export default App;
