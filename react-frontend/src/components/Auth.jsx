import { useState } from "react";
import { API } from "../api";

export default function Auth({ onLogin }) {
  const [isLogin, setIsLogin] = useState(true);

  const [form, setForm] = useState({
    username: "",
    password: ""
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async () => {
    try {
      if (isLogin) {
        // LOGIN
        const res = await API.post("/auth/login", form);

        if (res.data === "Login successful") {
          alert("Login success ✅");
          onLogin();
        } else {
          alert(res.data);
        }

      } else {
        // SIGNUP
        const res = await API.post("/auth/signup", form);
        alert(res.data);
        setIsLogin(true);
      }
    } catch (err) {
      console.error(err);
      alert("Server error ❌");
    }
  };

  return (
    <div className="auth-container">
      <h2>{isLogin ? "Login" : "Signup"}</h2>

      <input
        name="username"
        placeholder="Username"
        onChange={handleChange}
      />

      <input
        name="password"
        type="password"
        placeholder="Password"
        onChange={handleChange}
      />

      <button onClick={handleSubmit}>
        {isLogin ? "Login" : "Signup"}
      </button>

      <p onClick={() => setIsLogin(!isLogin)} className="toggle">
        {isLogin
          ? "Don't have an account? Signup"
          : "Already have an account? Login"}
      </p>
    </div>
  );
}