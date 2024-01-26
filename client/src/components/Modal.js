import AddDriverForm from "./ManagerDashboardComponents/AddDriverForm";
import '../styles/Modal.css'

const Modal = ({ closeModal }) => {

    return (
        <aside className="modal">
            <div className="modal-background">
                <div className="modal-container">
                    <section className="driver-form">
                        <button onClick={() => closeModal(false)} className="close-modal-button">X</button>
                        <AddDriverForm />
                    </section>
                </div>
            </div>
        </aside>

    );
}

export default Modal;