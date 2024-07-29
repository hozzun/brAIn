const MainCard = ({ isButtonClicked, children }) => {
  return (
    <div className={`w-11/12 home:w-[25rem] h-[31rem] perspective-1000`}>
      <div
        className={`w-full h-full relative transition-all preserve-3d duration-500 ${isButtonClicked && "rotate-180"}`}
      >
        {children}
      </div>
    </div>
  );
};

export default MainCard;