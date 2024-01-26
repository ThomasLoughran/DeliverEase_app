
const DistributionCentreDropdown = ({ distributionCentres, selectedCentreId, onChange }) => {
    return (
        <section>
            <label>Select Distribution Centre: </label>
            <select value={selectedCentreId || ''} onChange={(e) => onChange(parseInt(e.target.value, 10))}>
                <option value="" disabled>Select Distribution Centre</option>
                {distributionCentres.map((centre) => (
                    <option key={centre.id} value={centre.id}>
                        {centre.name}
                    </option>
                ))}
            </select>
        </section>
    );
};

export default DistributionCentreDropdown;
