import { useState } from "react";
import '../../styles/AddDriverForm.css'

const AddDriverForm = () => {

    const [formData, setFormData] = useState({
        name: "",
        vanCapacity: 0,
        vanMaxWeight: 0,
        vanName: "",
        password: "",
        distributionCentreId: null,
    });

    const handleFormDataChange = (e) => {
        const { name, value } = e.target;
        setFormData((previousData) => ({
            ...previousData, [name]: value,
        }))
    }

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        if (formData.confirmPassword !== formData.password) {
            alert('Passwords do not match.');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/drivers/new-driver', {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData)
            })

            if (!response.ok) {
                throw new Error(`Failed to add driver: ${response.status}`)
            } else {
                setFormData({
                    name: "",
                    vanCapacity: 0,
                    vanMaxWeight: 0,
                    vanName: "",
                    password: "",
                    confirmPassword: "",
                    distributionCentreId: null,
                })
            }

        } catch (error) {
            console.error(error);
        }
    };

    return (
        <>
            <form id="add-driver-form" onSubmit={handleFormSubmit} >
                <label htmlFor="name">
                    Name:
                </label>

                <input
                    placeholder="Please enter name here"
                    id="name"
                    type="text"
                    name="name"
                    value={formData.name}
                    onChange={handleFormDataChange}
                    required >

                </input>

                <label htmlFor="vanCapacity">
                    Van Capacity:
                </label>
                <input
                    placeholder="Please van capacity here"
                    id="vanCapacity"
                    type="number"
                    name="vanCapacity"
                    value={formData.vanCapacity}
                    onChange={handleFormDataChange}
                    min="1"
                    required
                >
                </input>

                <label htmlFor="vanMaxWeight">
                    Van Max Weight:
                </label>
                <input
                    placeholder="Please max weight here"
                    id="vanMaxWeight"
                    type="number"
                    name="vanMaxWeight"
                    value={formData.vanMaxWeight}
                    onChange={handleFormDataChange}
                    min="1"
                    required
                >
                </input>

                <label htmlFor="vanName">
                    Van Name:
                </label>
                <input
                    placeholder="Please van name here"
                    id="vanName"
                    type="text"
                    name="vanName"
                    value={formData.vanName}
                    onChange={handleFormDataChange}
                    required
                >
                </input>

                <label htmlFor="password">
                    Password:
                </label>
                <input
                    placeholder="Please enter password here"
                    id="password"
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleFormDataChange}
                    required
                >
                </input>

                <label htmlFor="confirmPassword">
                    Confirm Password:
                </label>
                <input
                    placeholder="Please confirm password here"
                    id="confirmPassword"
                    type="password"
                    name="confirmPassword"
                    value={formData.confirmPassword}
                    onChange={handleFormDataChange}
                    required
                >
                </input>

                <label htmlFor="distributionCentreId">
                    Distribution Centre ID:
                </label>

                <input
                    placeholder="Please enter distribution centre here"
                    id="distributionCentreId"
                    type="number"
                    name="distributionCentreId"
                    value={formData.distributionCentreId}
                    onChange={handleFormDataChange}
                    min="1"
                    required
                >
                </input>

                <button id="submit-button" type="submit"> Submit </button>

            </form>
        </>
    );
};

export default AddDriverForm;