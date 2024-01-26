import { useState } from "react";
import '../../styles/AddDriverForm.css'

const AddDriverForm = () => {

    const [formData, setFormData] = useState({
        name: "",
        vanCapacity: 0,
        vanMaxWeight: 0,
        vanName: "",
        password: "",
        distributionCentreId: "",
    });

    const handleFormDataChange = (e) => {
        const { name, value } = e.target;
        setFormData((previousData) => ({
            ...previousData, 
            // [name]: name === 'distributionCentreId' ? parseInt(value, 10) : value,
            [name] : value,
        }));
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        if (formData.confirmPassword !== formData.password) {
            alert('Passwords do not match.');
            return;
        }

        try {
            const distributionCentreMappings = {
                BIRMINGHAM: 1,
                BRISTOL: 2,
                LONDON: 3,
                MANCHESTER: 4,
                OXFORD: 5,
                CARDIFF: 6,
            };

            const distributionCentreId = distributionCentreMappings[formData.distributionCentreId];
            console.log("DistributionCentreId:", distributionCentreId)

            // if (distributionCentreId === undefined) {
            //     alert("Invalid Distribution Centre selected.");
            //     return;
            // }

            const dataToSend = {
                ...formData,
                distributionCentreId,
            };

            const response = await fetch('http://localhost:8080/drivers/new-driver', {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(dataToSend)
            })
            console.log("Data:", dataToSend)

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
                    distributionCentreId: "",
                })
            }

        } catch (error) {
            console.error(error);
        }
    };

    return (
        <>
            <section>
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
                        Distribution Centre:
                    </label>
                    <select
                        id="distributionCentreId"
                        name="distributionCentreId"
                        value={formData.distributionCentreId || ''}
                        onChange={handleFormDataChange}
                        required
                    >
                        <option value="" disabled>
                            Select Distribution Centre
                        </option>
                        <option value="1">BIRMINGHAM</option>
                        <option value="2">BRISTOL</option>
                        <option value="3">LONDON</option>
                        <option value="4">MANCHESTER</option>
                        <option value="5">OXFORD</option>
                        <option value="6">CARDIFF</option>
                    </select>

                    <button id="submit-button" type="submit"> Submit </button>

                </form>
            </section>
        </>
    );
};

export default AddDriverForm;