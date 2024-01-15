import AddDriverForm from "./ManagerDashboardComponents/AddDriverForm";

const Modal = ({closeModal}) => {

    return ( 
        <div className="modal-background">
            <div className="modal-container">
                <button onClick={() => closeModal(false)}>X</button>
                <div className="driver-form">
                    <AddDriverForm/>
                </div>
            </div>
        </div>
        
    );
}

export default Modal;