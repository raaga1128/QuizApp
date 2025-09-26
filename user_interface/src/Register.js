import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./style/Register.css";

const Register = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [isTeacher, setTeacher] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleRegister = async () => {
    if (!email || !username || !password || !phone) {
      toast.error("All fields are required.");
      return;
    }

    setLoading(true);

    try {
      const response = await axios.post("http://localhost:8082/register", {
        email,
        username,
        phone,
        password,
        isTeacher,
      });

      console.log(response.data);
      toast.success("Registration successful!");
      navigate("/"); // Navigate to login or dashboard
    } catch (error) {
      console.error(error);
      if (error.response) {
        toast.error(error.response.data.message || "Registration failed.");
      } else {
        toast.error("An unexpected error occurred. Please try again later.");
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <div className="parent">
        <div className="left">
          <div className="column-1">
            <img
              className="logo"
              src="assets/2.png"
              alt="Description of the logo"
            />
            <span className="span">
              <h1 className="text">Sign up to </h1>
              <h2 className="text">Our Webpage Quiz project </h2>
              <div className="Paragraphs">
                <p className="div-1">If you already have an account</p>
                <p className="div-2">
                  You can <Link to="/">Login here!</Link>
                </p>
              </div>
            </span>
          </div>
          <div className="column-2">
            <img
              loading="lazy"
              src="assets/1.png"
              className="img"
              alt="Human"
            />
          </div>
        </div>
        <div className="right">
          <h1 className="div-3">Sign up</h1>
          <input
            type="email"
            className="data"
            placeholder="Enter email"
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="text"
            className="data"
            placeholder="Create a username"
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            type="tel"
            className="data"
            placeholder="Phone number"
            onChange={(e) => setPhone(e.target.value)}
          />
          <input
            type="password"
            className="pass"
            placeholder="Password"
            onChange={(e) => setPassword(e.target.value)}
          />
          <div className="teacher-checkbox">
            <label>
              <input
                type="checkbox"
                checked={isTeacher}
                onChange={() => setTeacher(!isTeacher)}
              />
              Are you a teacher?
            </label>
          </div>
          <button className="register" onClick={handleRegister} disabled={loading}>
            {loading ? "Registering..." : "Register"}
          </button>
        </div>
      </div>
      <ToastContainer />
    </>
  );
};

export default Register;
