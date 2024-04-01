// import React from 'react'
// import { Footer, Navbar } from "../components";
// import { Link } from 'react-router-dom';
// const Register = () => {
//     return (
//         <>
//             <Navbar />
//             <section >
//             <div className="container my-3 py-3">
//                 <h1 className="text-center">Register</h1>
//                 <hr />
//                 <div class="row my-4 h-100">
//                     <div className="col-md-4 col-lg-4 col-sm-8 mx-auto">
//                         <form>
//                             <div class="form my-3">
//                                 <label for="Name">Full Name</label>
//                                 <input
//                                     type="email"
//                                     class="form-control"
//                                     id="Name"
//                                     placeholder="Enter Your Name"
//                                     />
//                             </div>
//                             <div class="form my-3">
//                                 <label for="Email">Email address</label>
//                                 <input
//                                     type="email"
//                                     class="form-control"
//                                     id="Email"
//                                     placeholder="name@example.com"
//                                     />
//                             </div>
//                             <div class="form  my-3">
//                                 <label for="Password">Password</label>
//                                 <input
//                                     type="password"
//                                     class="form-control"
//                                     id="Password"
//                                     placeholder="Password"
//                                     />
//                             </div>
//                             <div className="my-3">
//                                 <p>Already has an account? <Link to="/login" className="text-decoration-underline text-info">Login</Link> </p>
//                             </div>
//                             <div className="text-center">
//                                 <button class="my-2 mx-auto btn btn-dark" type="submit" disabled>
//                                     Register
//                                 </button>
//                             </div>
//                         </form>
//                     </div>
//                 </div>
//             </div>
//    </section>
//    <Footer/>
//         </>
//     )
// }

// export default Register



import React, { useState } from 'react';
import { Footer, Navbar } from "../components";
import { Link } from 'react-router-dom';
import axios from 'axios';

const Register = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: ''
    });

    const { name, email, password } = formData;

    const onChange = e => setFormData({ ...formData, [e.target.name]: e.target.value });

    const onSubmit = async e => {
        e.preventDefault();
        try {
            const config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };

            const body = JSON.stringify({ name, email, password });

            const res = await axios.post('http://localhost:8080/register', body, config);

            console.log(res.data); // Do something with the response data, such as redirecting the user or displaying a success message
        } catch (err) {
            console.error(err.response.data);
        }
    };

    return (
        <>
            <Navbar />
            <section >
                <div className="container my-3 py-3">
                    <h1 className="text-center">Register</h1>
                    <hr />
                    <div className="row my-4 h-100">
                        <div className="col-md-4 col-lg-4 col-sm-8 mx-auto">
                            <form onSubmit={onSubmit}>
                                <div className="form my-3">
                                    <label htmlFor="Name">Full Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="Name"
                                        name="name"
                                        value={name}
                                        onChange={onChange}
                                        placeholder="Enter Your Name"
                                    />
                                </div>
                                <div className="form my-3">
                                    <label htmlFor="Email">Email address</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        id="Email"
                                        name="email"
                                        value={email}
                                        onChange={onChange}
                                        placeholder="name@example.com"
                                    />
                                </div>
                                <div className="form  my-3">
                                    <label htmlFor="Password">Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="Password"
                                        name="password"
                                        value={password}
                                        onChange={onChange}
                                        placeholder="Password"
                                    />
                                </div>
                                <div className="my-3">
                                    <p>Already have an account? <Link to="/login" className="text-decoration-underline text-info">Login</Link> </p>
                                </div>
                                <div className="text-center">
                                    <button className="my-2 mx-auto btn btn-dark" type="submit">
                                        Register
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}

export default Register;
