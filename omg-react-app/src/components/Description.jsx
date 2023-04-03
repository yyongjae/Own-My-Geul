import React from 'react';
import styles from './Description.module.css';

function Description(props) {
    return (
        <div>
            {props.message}
        </div>
    );
}

export default Description;