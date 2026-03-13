import React, { useState, useRef } from 'react'
import Header from '../components/Header'
import { useNavigate } from 'react-router-dom'


function TodoEditNew() {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [titleError, setTitleError] = useState(false);
    const [titleEmpty, setTitleEmpty] = useState(false);
    const [descError, setDescError] = useState(false);
    const [descEmpty, setDescEmpty] = useState(false);
    const [imageFile, setImageFile] = useState(null);
    const [imagePreview, setImagePreview] = useState(null);
    
    const inputRef = useRef(null);
    const onFileInputChange = (e) => {
        const file = e.target.files[0];
        setImageFile(file);
        setImagePreview(URL.createObjectURL(file));
    };

    const handleRegister = () => {
        if (title === '') {
            setTitleEmpty(true);
        }
        if (description === '') {
            setDescEmpty(true);
        }
        if (title === '' || description === '') {
            return;
        }
    }
  return (
    <div>
        <Header />
        <div className='body_editAll'>
            <div className='body_editnew'>
                <div className='imageSize'>
                    {imagePreview
                    ?
                    <img src={imagePreview} alt={'Preview'} />
                    :
                    <div className='no-imagePreview'>No Image</div>
                    }
                </div>
                <div className='form_area'>
                    <div className='editElement'>
                        <label>タイトル<span style={{color:'red'}}>*</span></label>
                        <input 
                            type="text" 
                            value={title}
                            onChange={(e) => {
                                setTitle(e.target.value);
                                setTitleError(e.target.value.length > 30);
                                setTitleEmpty(false);
                            }}
                        />
                        <p className='error_message'>
                            {titleError ? '30文字以下で入力してください' : ''}
                            {titleEmpty ? '※未記入です。' : ''}
                        </p>
                    </div>
                    <div className='editElement'>
                        <div className='element'>
                            <label>説明<span style={{color:'red'}}>*</span></label>
                        </div>
                        <textarea
                            value={description} 
                            onChange={(e) => {
                                setDescription(e.target.value);
                                setDescError(e.target.value.length > 150);
                                setDescEmpty(false);
                            }}
                        />
                        <p className='error_message'>
                            {descError ? '150文字以下で入力してください' : ''}
                            {descEmpty ? '※未記入です。' : ''}
                        </p>
                    </div>
                    <div className='fileSelect'>
                        <p>画像</p>
                        <button 
                            onClick={() => inputRef.current.click()}
                            type='button'
                            className='btn_todoimage'
                        >ファイルを選択</button>
                        <input 
                            hidden
                            ref={inputRef}
                            type='file'
                            accept='image/*'
                            onChange={onFileInputChange}
                        />
                    </div>
                </div>
            </div>
            <br />
            <div className='btn_bottom'>
                <button
                    onClick={() => navigate('/')}
                    className='btn_back'
                >戻る</button>
                <button
                    onClick={handleRegister}
                    className='btn_register'
                >登録</button>
            </div>
        </div>
    </div>
  )
}

export default TodoEditNew